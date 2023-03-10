package com.daniel.projects.dataproducermicroservice.service;

import com.daniel.projects.dataproducermicroservice.model.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class KafkaEndpointService {

    private final WebClient webClient;
    private final String KAFKA_ENDPOINT = "/articles";

    @Autowired
    public KafkaEndpointService (@Qualifier("webPostArticlesClient") WebClient webClient) {
        this.webClient = webClient;
    }

    public void sendArticles(List<Article> articles) {
        for (Article article : articles) {
            Mono<String> response = webClient.post()
                    .uri(KAFKA_ENDPOINT)
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(article)
                    .retrieve()
                    .bodyToMono(String.class);

            response.subscribe(
                    body -> System.out.println("Response body: " + body),
                    error -> System.err.println("Error: " + error.getMessage()),
                    () -> System.out.println("Request completed.")
            );
        }
    }
}
