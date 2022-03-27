package smart.ad.founder.demo.application.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import smart.ad.founder.demo.domain.model.DTOs.KafkaFoundAdMessage;

@Component
public class KafkaMessageListener {

    @Autowired
    SimpMessagingTemplate template;

    // the method that will listen for the Kafka queue messages
    @KafkaListener(
            topics = "kafka-smartAdFounder",
            groupId = "kafka-sandbox"
    )
    public void listen(KafkaFoundAdMessage message) {
        System.out.println(" -------------------- SENDING VIA KAFKA LISTENER");
         template.convertAndSend("/topic/group", message);   // convert the message and send it to the webSocket topic
//        template.convertAndSend("/api/kafkaMessages/topic/group", message);
    }

}
