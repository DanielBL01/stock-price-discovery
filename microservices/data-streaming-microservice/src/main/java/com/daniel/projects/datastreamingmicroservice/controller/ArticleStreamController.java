package com.daniel.projects.datastreamingmicroservice.controller;

import com.daniel.projects.datastreamingmicroservice.ArticleSSEConsumer;
import com.daniel.projects.datastreamingmicroservice.model.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping(value = "/stream")
public class ArticleStreamController {

    private final ArticleSSEConsumer articleSSEConsumer;

    @Autowired
    public ArticleStreamController(ArticleSSEConsumer articleSSEConsumer) {
        this.articleSSEConsumer = articleSSEConsumer;
    }

    @GetMapping(value = "/articles", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ServerSentEvent<Article>> getArticles() {
        return articleSSEConsumer.getFlux();
    }
}
