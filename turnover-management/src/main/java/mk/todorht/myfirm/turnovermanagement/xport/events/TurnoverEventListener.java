package mk.todorht.myfirm.turnovermanagement.xport.events;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import mk.todorht.myfirm.sharedkernel.events.config.TopicHolder;
import mk.todorht.myfirm.sharedkernel.events.turnover.InvoiceCreated;
import mk.todorht.myfirm.turnovermanagement.service.TurnoverService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TurnoverEventListener {

    private final TurnoverService service;

    @KafkaListener(topics = TopicHolder.TOPIC_INVOICE_CREATED,
            containerFactory = "invoiceCreatedKafkaListenerFactory")
    public void salaryItemCreatedEvent(String jsonMessage){
        ObjectMapper objectMapper = new ObjectMapper();
        InvoiceCreated invoiceCreated = null;
        try {
            invoiceCreated = objectMapper.readValue(jsonMessage, InvoiceCreated.class);
        }catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        service.increaseTurnoverAmount(invoiceCreated);

    }
}
