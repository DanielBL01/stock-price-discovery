package com.daniel.projects.datastreamingmicroservice.repository;

import com.daniel.projects.datastreamingmicroservice.model.News;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsRepository extends JpaRepository<News, Long> {
}
