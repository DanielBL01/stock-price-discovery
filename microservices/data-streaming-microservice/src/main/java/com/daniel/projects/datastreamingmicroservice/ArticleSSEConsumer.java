package com.daniel.projects.datastreamingmicroservice;

import com.daniel.projects.datastreamingmicroservice.model.Article;
import com.daniel.projects.datastreamingmicroservice.model.ArticleSSE;
import com.daniel.projects.datastreamingmicroservice.service.LinkCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

@Component
public class ArticleSSEConsumer {

    private final Sinks.Many<ArticleSSE> sink;
    private final LinkCacheService linkCacheService;

    @Autowired
    public ArticleSSEConsumer(LinkCacheService linkCacheService) {
        this.sink = Sinks.many().multicast().onBackpressureBuffer();
        this.linkCacheService = linkCacheService;
    }

    @KafkaListener(topics = "articles", groupId = "article-sse-group",
            containerFactory = "sseListenerContainerFactory")
    public void consumeAndSSE(Article article) {
        String link = article.getLink();
        if (linkCacheService.exists(link)) {
            System.out.println("\nINFO: Link already exists in the data stream\n");
        } else {
            ArticleSSE articleSSE = new ArticleSSE(
                    article.getTitle(),
                    article.getAuthor(),
                    article.getSummary(),
                    article.getLink(),
                    article.getPublishedDate()
            );
            sink.tryEmitNext(articleSSE);
            linkCacheService.add(link);
        }
    }

    public Flux<ServerSentEvent<ArticleSSE>> getFlux() {
        return sink.asFlux()
                .map(articleSSE -> ServerSentEvent.builder(articleSSE)
                        .build());
    }
}
