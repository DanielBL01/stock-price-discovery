package com.daniel.projects.datastreamingmicroservice.controller;

import com.daniel.projects.datastreamingmicroservice.ArticleSSEConsumer;
import com.daniel.projects.datastreamingmicroservice.model.ArticleSSE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping(value = "/stream")
public class ArticleStreamController {

    private final ArticleSSEConsumer articleSSEConsumer;

    @Autowired
    public ArticleStreamController(ArticleSSEConsumer articleSSEConsumer) {
        this.articleSSEConsumer = articleSSEConsumer;
    }

    @GetMapping(value = "/articles", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ServerSentEvent<ArticleSSE>> getArticleStreams() {
        return articleSSEConsumer.getFlux();
    }
}
