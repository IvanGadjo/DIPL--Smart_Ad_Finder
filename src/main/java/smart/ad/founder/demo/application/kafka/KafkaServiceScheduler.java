package smart.ad.founder.demo.application.kafka;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import smart.ad.founder.demo.application.service.FoundAdvertService;
import smart.ad.founder.demo.domain.model.DTOs.KafkaFoundAdMessage;
import smart.ad.founder.demo.domain.model.FactoryClass;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class KafkaServiceScheduler {

    @Autowired
    private KafkaTemplate<String, KafkaFoundAdMessage> kafkaTemplate;

    private FoundAdvertService foundAdvertService;
    private FactoryClass factory;

    public KafkaServiceScheduler(FoundAdvertService foundAdvertService, FactoryClass factory) {
        this.foundAdvertService = foundAdvertService;
        this.factory = factory;
    }



     @Scheduled(fixedDelay = 30000)      // ova e na 30 sekundi
    public void sendMessageToKafka(){
        List<KafkaFoundAdMessage> kafkaMessages = new ArrayList<>();

         // * Gi zema site mozni found adverts sto se alreadyShownToUser = false
         // * vadi user info od userInterestot
         // * stavanje alreadyShownToUser = true vo KafkaMessageListener -> pred da se pratat do websocket

        foundAdvertService.findAllFoundAdverts().forEach(fa -> {
            // fa.getUserInterest().getUser().getUserEmail();
            // fa.getUserInterest().getUser().getId();

            if(!fa.getAlreadyShownToUser()) {
                kafkaMessages.add(factory.createNewKafkaFoundAdMessage(fa.getId(), fa.getUrl(), fa.getImageUrl(), fa.getTitle(),
                        fa.getPrice(), fa.getUserInterest().getId(), fa.getUserInterest().getUser().getUserEmail()));
            }
        });

        System.out.println("----------------- Kafka message 30 secs ---------------");

        kafkaMessages.forEach(km -> {
            try {
                // * Sending the message to kafka topic queue
                // System.out.println("**********Send");
                kafkaTemplate.send("kafka-smartAdFounder", km).get();
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
