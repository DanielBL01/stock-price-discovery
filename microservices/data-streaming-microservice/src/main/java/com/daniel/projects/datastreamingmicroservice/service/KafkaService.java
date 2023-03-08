package com.daniel.projects.datastreamingmicroservice.service;

import com.daniel.projects.datastreamingmicroservice.model.Test;
import com.daniel.projects.datastreamingmicroservice.repository.KafkaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KafkaService {

    private final KafkaRepository kafkaRepository;

    @Autowired
    public KafkaService(KafkaRepository kafkaRepository) {
        this.kafkaRepository = kafkaRepository;
    }

    public Test saveMessage(Test model) {
        return kafkaRepository.save(model);
    }
}
