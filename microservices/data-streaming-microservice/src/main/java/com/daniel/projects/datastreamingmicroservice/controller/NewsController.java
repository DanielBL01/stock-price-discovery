package com.daniel.projects.datastreamingmicroservice.controller;

import com.daniel.projects.datastreamingmicroservice.model.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/articles")
public class NewsController {

    private final KafkaTemplate<String, Article> kafkaTemplate;
    private final String TOPIC_NAME = "articles";

    @Autowired
    public NewsController(KafkaTemplate kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @PostMapping
    public void sendNews(@RequestBody Article article) {
        kafkaTemplate.send(TOPIC_NAME, article);
    }
}
