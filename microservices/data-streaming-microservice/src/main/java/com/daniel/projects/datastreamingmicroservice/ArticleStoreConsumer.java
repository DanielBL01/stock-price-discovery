package com.daniel.projects.datastreamingmicroservice;

import com.daniel.projects.datastreamingmicroservice.exception.InvalidNewsException;
import com.daniel.projects.datastreamingmicroservice.model.Article;
import com.daniel.projects.datastreamingmicroservice.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class ArticleStoreConsumer {

    private final NewsService newsService;

    @Autowired
    public ArticleStoreConsumer(NewsService newsService) {
        this.newsService = newsService;
    }

    @KafkaListener(topics = "articles", groupId = "article-store-group",
            containerFactory = "kafkaListenerContainerFactory")
    public void consumeAndStore(Article article) {
        try {
            if (!article.hasAllRequiredFields()) {
                throw new InvalidNewsException("Article object does not have all required fields");
            }
            newsService.saveArticle(article);
            System.out.println("\nSUCCESS: Consumed and Stored: " + article.getTitle() + " from topic articles\n");
        } catch (InvalidNewsException e) {
            System.out.println("\nFAILED with InvalidNewsException: " + e.getMessage() + "\n");
        } catch(DataIntegrityViolationException e) {
            System.out.println("\nFAILED with DataIntegrityViolationException: " + e.getMessage() + "\n");
        } catch (Exception e) {
            System.out.println("\nFAILED with unknown exception: " + e.getMessage() + "\n");
        }
    }
}
