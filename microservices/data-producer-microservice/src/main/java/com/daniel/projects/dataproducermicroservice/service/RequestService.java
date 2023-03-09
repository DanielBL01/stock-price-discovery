package com.daniel.projects.dataproducermicroservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@PropertySource("classpath:env.properties")
public class RequestService {

    private final WebClient webClient;
    @Value("${api.key}")
    private String API_KEY;

    @Autowired
    public RequestService(WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<String> getData() {
        return webClient.get()
                .uri("latest_headlines?countries=US")
                .header("X-API-Key", API_KEY)
                .retrieve()
                .bodyToMono(String.class);
    }
}
