package com.daniel.projects.datastreamingmicroservice.controller;

import com.daniel.projects.datastreamingmicroservice.model.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/test")
public class KafkaController {

    private final KafkaTemplate<String, Test> kafkaTemplate;
    private final String TOPIC_NAME = "test";

    @Autowired
    public KafkaController(KafkaTemplate kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @PostMapping
    public void sendMessage(@RequestBody Test test) {
        kafkaTemplate.send(TOPIC_NAME, test);
    }
}
