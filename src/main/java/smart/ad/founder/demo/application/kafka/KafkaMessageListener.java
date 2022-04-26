package smart.ad.founder.demo.application.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import smart.ad.founder.demo.application.service.FoundAdvertService;
import smart.ad.founder.demo.domain.model.DTOs.KafkaFoundAdMessage;
import smart.ad.founder.demo.domain.model.entities.FoundAdvert;

@Component
public class KafkaMessageListener {

    @Autowired
    SimpMessagingTemplate template;

    @Autowired
    FoundAdvertService foundAdvertService;

    // * the method that will listen for the Kafka queue messages
    @KafkaListener(
            topics = "kafka-smartAdFounder",
            groupId = "kafka-sandbox"
    )


    public void listen(KafkaFoundAdMessage message) {
        System.out.println(" -------------------- LISTEN FOR KAFKA QUEUE, SEND MSG TO WEBSOCKET");

        // ! UNCOMMENT TO SET ALREADY SHOWN TO USER => TRUE
//        FoundAdvert foundAd = foundAdvertService.findFoundAdvertById(message.getFoundAdId());
//        foundAd.setAlreadyShownToUser(true);
//        foundAdvertService.editFoundAdvert(foundAd, foundAd.getUserInterest().getId());

        template.convertAndSend("/topic/group", message);   // convert the message and send it to the webSocket topic
        // template.convertAndSend("/api/kafkaMessages/topic/group", message);
    }


}
