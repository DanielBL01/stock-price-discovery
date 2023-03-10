package com.daniel.projects.datastreamingmicroservice;

import com.daniel.projects.datastreamingmicroservice.exception.InvalidNewsException;
import com.daniel.projects.datastreamingmicroservice.model.Article;
import com.daniel.projects.datastreamingmicroservice.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class NewsConsumer {

    private final NewsService newsService;

    @Autowired
    public NewsConsumer(NewsService newsService) {
        this.newsService = newsService;
    }

    @KafkaListener(topics = "articles", groupId = "article-consumer-group")
    public void listen(Article article) {
        try {
            if (!article.hasAllRequiredFields()) {
                throw new InvalidNewsException("FAILED: Article object does not have all required fields");
            }
            newsService.saveArticle(article);
            System.out.println("SUCCESS: Got article titled: " + article.getTitle() + " from topic articles");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
