package mk.todorht.myfirm.turnovermanagement.service.impl;

import mk.todorht.myfirm.sharedkernel.events.config.TopicHolder;
import mk.todorht.myfirm.sharedkernel.events.turnover.AchievedBonus;
import mk.todorht.myfirm.sharedkernel.events.turnover.AchievedLimit;
import mk.todorht.myfirm.sharedkernel.events.turnover.InvoiceCreated;
import mk.todorht.myfirm.sharedkernel.financial.Money;
import mk.todorht.myfirm.sharedkernel.repository.GenericRepository;
import mk.todorht.myfirm.sharedkernel.services.impl.GenericServiceImpl;
import mk.todorht.myfirm.sharedkernel.BusinessRules;
import mk.todorht.myfirm.turnovermanagement.domain.model.Turnover;
import mk.todorht.myfirm.turnovermanagement.domain.model.TurnoverId;
import mk.todorht.myfirm.turnovermanagement.domain.repository.TurnoverRepository;
import mk.todorht.myfirm.turnovermanagement.service.TurnoverService;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@Transactional
public class TurnoverServiceImpl extends GenericServiceImpl<Turnover, TurnoverId> implements TurnoverService {

    private final KafkaTemplate<String, AchievedLimit> kafkaLimitTemplate;
    private final KafkaTemplate<String, AchievedBonus> kafkaBonusTemplate;

    public TurnoverServiceImpl(TurnoverRepository repository, KafkaTemplate<String, AchievedLimit> kafkaLimitTemplate, KafkaTemplate<String, AchievedBonus> kafkaBonusTemplate) {
        super(repository);
        this.kafkaLimitTemplate = kafkaLimitTemplate;
        this.kafkaBonusTemplate = kafkaBonusTemplate;
    }

    private void eventsChecker(Turnover turnover, double amount, boolean achieved){
        if(amount>= BusinessRules.AMOUNT_LIMIT && !achieved){
            kafkaLimitTemplate.send(TopicHolder.TOPIC_ACHIEVED_LIMIT, new AchievedLimit(turnover.getTurnoverId().getEmployeeId()
                    ,turnover.getTurnoverId().getMonth()
                    ,turnover.getTurnoverId().getYear()));
            turnover.markAsAchieved();
            save(turnover);
        }
        if(amount> BusinessRules.BONUS_LIMIT && amount- BusinessRules.BONUS_LIMIT>=50000){
            int tmp = (int) ((amount- BusinessRules.BONUS_LIMIT)/50000);
            Money bonus_amount = BusinessRules.BONUS.multiply(tmp);
            kafkaBonusTemplate.send(TopicHolder.TOPIC_ACHIEVED_BONUS, new AchievedBonus(turnover.getTurnoverId().getEmployeeId()
                    ,turnover.getTurnoverId().getMonth()
                    ,turnover.getTurnoverId().getYear(),
                    bonus_amount));
        }
    }

    @Override
    public Turnover create(int employeeId, int month, int year) {
        TurnoverId turnoverId = new TurnoverId(employeeId, month, year);
        if(this.findById(turnoverId).isEmpty()){
            return save(new Turnover(turnoverId));
        }
        return findById(turnoverId).get();
    }

    @Override
    public void increaseTurnoverAmount(InvoiceCreated invoiceCreated) {
        LocalDate localDate = LocalDate.parse(invoiceCreated.getCreateAt());
        Turnover turnover = this.create(invoiceCreated.getEmployeeId(),localDate.getMonthValue(),localDate.getYear());
        turnover.addAmount(turnover.getTotal().add(invoiceCreated.getAmount()));
        save(turnover);
        eventsChecker(turnover,turnover.getTotal().getAmount(),turnover.isAchievedLimit());
    }
}
