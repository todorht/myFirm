package mk.todorht.myfirm.salarymanagement.xport.events;



import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import mk.todorht.myfirm.salarymanagement.domain.model.SalaryId;
import mk.todorht.myfirm.salarymanagement.sevices.SalaryService;
import mk.todorht.myfirm.sharedkernel.events.config.TopicHolder;
import mk.todorht.myfirm.sharedkernel.events.salary.SalaryItemCreated;
import mk.todorht.myfirm.sharedkernel.events.turnover.AchievedBonus;
import mk.todorht.myfirm.sharedkernel.events.turnover.AchievedLimit;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SalaryEventListener {

    private final SalaryService salaryService;
    private final ObjectMapper objectMapper;

    @KafkaListener(topics = TopicHolder.TOPIC_SALARY_ITEM_CREATED, groupId= "salaryManagement",
            containerFactory = "salaryKafkaListenerFactory")
    public void salaryItemCreatedEvent(String jsonMessage){

        SalaryItemCreated salaryItem = null;
        try {
            salaryItem = objectMapper.readValue(jsonMessage, SalaryItemCreated.class);
        }catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        salaryService.addSalaryItem(salaryItem);

    }

    @KafkaListener(topics = TopicHolder.TOPIC_ACHIEVED_LIMIT, containerFactory = "salaryKafkaListenerFactory")
    public void salaryLimitEvent(String jsonMessage){

        AchievedLimit achievedLimit = null;
        try {
            achievedLimit = objectMapper.readValue(jsonMessage, AchievedLimit.class);
        }catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        salaryService.removePenalty(new SalaryId(achievedLimit.getEmployeeId(), achievedLimit.getMonth(), achievedLimit.getYear()));
    }

    @KafkaListener(topics = TopicHolder.TOPIC_ACHIEVED_BONUS, containerFactory = "salaryKafkaListenerFactory")
    public void salaryBonusEvent(String jsonMessage){

        AchievedBonus achievedBonus = null;
        try {
            achievedBonus = objectMapper.readValue(jsonMessage, AchievedBonus.class);
        }catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        salaryService.addBonus(new SalaryId(achievedBonus.getEmployeeId(), achievedBonus.getMonth(), achievedBonus.getYear()),achievedBonus.getBonus());
    }
}
