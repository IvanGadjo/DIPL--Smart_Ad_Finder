package smart.ad.founder.demo.web.kafka_and_websocket;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.lang.Nullable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.*;
import smart.ad.founder.demo.domain.model.DTOs.KafkaFoundAdMessage;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/kafkaMessages")
@CrossOrigin("*")
public class KafkaController {


    @Autowired
    private KafkaTemplate<String, KafkaFoundAdMessage> kafkaTemplate;


    // TODO: Shut down this endpoint after everything works with the KafkaServiceScheduler
    @PostMapping(value = "/send", consumes = "application/json", produces = "application/json")
    public void sendMessage(@RequestBody KafkaFoundAdMessage kafkaFoundAdMessage) {

        kafkaFoundAdMessage.setUrl("testUrlAndrej.com");
        kafkaFoundAdMessage.setUserInterestId(123L);

        try {
            //Sending the message to kafka topic queue
            kafkaTemplate.send("kafka-smartAdFounder", kafkaFoundAdMessage).get();

        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }


    }



    //    -------------- WebSocket API ----------------
//    @MessageMapping("/kafka-chat")
//    @SendTo("/topic/group")
//    public KafkaFoundAdMessage broadcastGroupMessage(@Payload KafkaFoundAdMessage message) {
//        //Sending this message to all the subscribers
//        System.out.println("-------------------- SENDING VIA WEBSOCKET");
//        return message;
//    }
}
