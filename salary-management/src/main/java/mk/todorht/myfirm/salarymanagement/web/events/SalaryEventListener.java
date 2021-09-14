package mk.todorht.myfirm.salarymanagement.web.events;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import mk.todorht.myfirm.salarymanagement.sevices.SalaryService;
import mk.todorht.myfirm.sharedkernel.events.DomainEvent;
import mk.todorht.myfirm.sharedkernel.events.config.TopicHolder;
import mk.todorht.myfirm.sharedkernel.events.salary.SalaryItemCreated;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SalaryEventListener {

    private final SalaryService salaryService;

    @KafkaListener(topics = TopicHolder.TOPIC_SALARY_ITEM_CREATED, groupId= "salaryManagement",
            containerFactory = "salaryKafkaListenerFactory")
    public void salaryItemCreatedEvent(String jsonMessage){
        try {

            salaryService.addSalaryItem(jsonMessage);
        }catch (Exception e){

        }
    }
}
