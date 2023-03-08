package com.daniel.projects.datastreamingmicroservice.repository;

import com.daniel.projects.datastreamingmicroservice.model.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KafkaRepository extends JpaRepository<Test, Long> {

}
