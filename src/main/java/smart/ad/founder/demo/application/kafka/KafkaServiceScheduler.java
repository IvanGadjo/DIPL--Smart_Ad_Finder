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

    // TODO: Mozebi treba da oznacuvas site poraki koi vekje se prateni na kafka?

     @Scheduled(fixedDelay = 30000)      // ova e na 30 sekundi
    public void sendMessageToKafka(){
        List<KafkaFoundAdMessage> kafkaMessages = new ArrayList<>();

        foundAdvertService.findAllFoundAdverts().forEach(fa -> {
            kafkaMessages.add(factory.createNewKafkaFoundAdMessage(fa.getUrl(), fa.getUserInterest().getId()));
        });

        System.out.println("----------------- Kafka message 30 secs ---------------");

        kafkaMessages.forEach(km -> {
            try {
                //Sending the message to kafka topic queue
                System.out.println("**********Send");
                kafkaTemplate.send("kafka-smartAdFounder", km).get();
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
