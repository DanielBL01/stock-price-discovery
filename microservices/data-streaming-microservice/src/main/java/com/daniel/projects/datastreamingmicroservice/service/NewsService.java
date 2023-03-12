package com.daniel.projects.datastreamingmicroservice.service;

import com.daniel.projects.datastreamingmicroservice.model.Article;
import com.daniel.projects.datastreamingmicroservice.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class NewsService {

    private final NewsRepository newsRepository;

    @Autowired
    public NewsService(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    public void saveArticle(Article article) throws DataIntegrityViolationException {
        newsRepository.save(article);
    }
}
