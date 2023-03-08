package com.daniel.projects.datastreamingmicroservice;

import com.daniel.projects.datastreamingmicroservice.model.Test;
import com.daniel.projects.datastreamingmicroservice.service.KafkaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class KafkaConsumer {

    private final KafkaService kafkaService;

    @Autowired
    public KafkaConsumer(KafkaService kafkaService) {
        this.kafkaService = kafkaService;
    }

    @KafkaListener(topics = "test", groupId = "test")
    public void listen(Test test) throws IOException {
        kafkaService.saveMessage(test);
        System.out.println("Received message: " + test.getMessage() + " from topic test");
    }
}
