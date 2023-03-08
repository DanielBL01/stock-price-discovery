package com.daniel.projects.kafkaconsumermicroservice.repository;

import com.daniel.projects.kafkaconsumermicroservice.model.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KafkaRepository extends JpaRepository<Test, Long> {

}
