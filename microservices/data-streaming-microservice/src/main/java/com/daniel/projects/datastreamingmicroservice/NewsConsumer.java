package com.daniel.projects.datastreamingmicroservice;

import com.daniel.projects.datastreamingmicroservice.model.News;
import com.daniel.projects.datastreamingmicroservice.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class NewsConsumer {

    private final NewsService newsService;

    @Autowired
    public NewsConsumer(NewsService newsService) {
        this.newsService = newsService;
    }

    @KafkaListener(topics = "news", groupId = "news-consumer-group")
    public void listen(News news) throws IOException {
        newsService.saveNews(news);
        System.out.println("Got news titled: " + news.getTitle() + " from topic news");
    }
}
