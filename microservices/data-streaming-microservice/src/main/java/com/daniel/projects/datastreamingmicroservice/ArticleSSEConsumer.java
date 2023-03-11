package com.daniel.projects.datastreamingmicroservice;

import com.daniel.projects.datastreamingmicroservice.model.Article;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

@Component
public class ArticleSSEConsumer {

    private final Sinks.Many<Article> sink;

    public ArticleSSEConsumer() {
        this.sink = Sinks.many().multicast().onBackpressureBuffer();
    }

    @KafkaListener(topics = "articles", groupId = "article-sse-group",
            containerFactory = "sseListenerContainerFactory")
    public void consumeAndSSE(Article article) {
        System.out.println("SSE-GROUP SUCCESS: " + article.getTitle());
        sink.tryEmitNext(article);
    }

    public Flux<ServerSentEvent<Article>> getFlux() {
        return sink.asFlux()
                .map(article -> ServerSentEvent.builder(article)
                        .event("article")
                        .build());
    }
}
