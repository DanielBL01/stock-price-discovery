package com.daniel.projects.datastreamingmicroservice;

import com.daniel.projects.datastreamingmicroservice.model.Article;
import com.daniel.projects.datastreamingmicroservice.model.ArticleSSE;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

@Component
public class ArticleSSEConsumer {

    private final Sinks.Many<ArticleSSE> sink;

    public ArticleSSEConsumer() {
        this.sink = Sinks.many().multicast().onBackpressureBuffer();
    }

    @KafkaListener(topics = "articles", groupId = "article-sse-group",
            containerFactory = "sseListenerContainerFactory")
    public void consumeAndSSE(Article article) {
        System.out.println("SSE SUCCESS: " + article.getTitle());
        ArticleSSE articleSSE = new ArticleSSE(
                article.getTitle(),
                article.getAuthor(),
                article.getSummary(),
                article.getLink(),
                article.getPublishedDate()
        );
        sink.tryEmitNext(articleSSE);
    }

    public Flux<ServerSentEvent<ArticleSSE>> getFlux() {
        return sink.asFlux()
                .map(articleSSE -> ServerSentEvent.builder(articleSSE)
                        .build());
    }
}
