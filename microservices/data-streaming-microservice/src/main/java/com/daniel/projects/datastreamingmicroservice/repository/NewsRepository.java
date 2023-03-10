package com.daniel.projects.datastreamingmicroservice.repository;

import com.daniel.projects.datastreamingmicroservice.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsRepository extends JpaRepository<Article, Long> {
}
