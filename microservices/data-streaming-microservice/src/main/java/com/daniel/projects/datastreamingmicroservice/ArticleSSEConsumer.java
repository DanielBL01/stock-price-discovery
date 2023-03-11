package com.daniel.projects.datastreamingmicroservice;

import com.daniel.projects.datastreamingmicroservice.model.Article;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class ArticleSSEConsumer {

    @KafkaListener(topics = "articles", groupId = "article-sse-group",
            containerFactory = "sseListenerContainerFactory")
    public void consumeAndSSE(Article article) {
        System.out.println("SSE-GROUP SUCCESS: " + article.getTitle());
    }
}
