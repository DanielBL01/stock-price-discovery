package com.daniel.projects.datastreamingmicroservice;

import com.daniel.projects.datastreamingmicroservice.exception.InvalidNewsException;
import com.daniel.projects.datastreamingmicroservice.model.News;
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

    @KafkaListener(topics = "news", groupId = "news-consumer-group")
    public void listen(News news) {
        try {
            if (!news.hasAllRequiredFields()) {
                throw new InvalidNewsException("FAILED: News object does not have all required fields");
            }
            newsService.saveNews(news);
            System.out.println("SUCCESS: Got news titled: " + news.getTitle() + " from topic news");
        } catch (Exception e) {
            System.out.println("FAILED: with unknown error: " + e.getMessage());
        }
    }
}
