package com.daniel.projects.dataproducermicroservice.service;

import com.daniel.projects.dataproducermicroservice.exception.StatusNotOkException;
import com.daniel.projects.dataproducermicroservice.model.Article;
import com.daniel.projects.dataproducermicroservice.model.NewsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class RequestService {

    private final WebClient webClient;
    private final KafkaEndpointService kafkaEndpointService;

    private final String API_URI = "/latest_headlines?" +
            "countries={countries}&" +
            "topic={topic}&" +
            "when={when}&" +
            "lang={language}&" +
            "page={page}&" +
            "page_size={pageSize}";
    private final String COUNTRIES = "US";
    private final String TOPICS = "business";
    private final String WHEN = "1h";
    private final String LANGUAGE = "en";
    private final int PAGE_SIZE = 50;

    @Autowired
    public RequestService(@Qualifier("webGetNewsClient") WebClient webClient,
                          KafkaEndpointService kafkaEndpointService) {
        this.webClient = webClient;
        this.kafkaEndpointService = kafkaEndpointService;
    }

    public void pollNewsData() {
        int page = 1;
        boolean hasUnseenPages = true;

        while (hasUnseenPages) {
            try {
                String apiURI = API_URI.replace("{countries}", COUNTRIES)
                        .replace("{topic}", TOPICS)
                        .replace("{when}", WHEN)
                        .replace("{language}", LANGUAGE)
                        .replace("{page}", Integer.toString(page))
                        .replace("{pageSize}", Integer.toString(PAGE_SIZE));

                NewsResponse response = webClient.get()
                        .uri(apiURI)
                        .accept(MediaType.APPLICATION_JSON)
                        .retrieve()
                        .bodyToMono(NewsResponse.class)
                        .block();

                System.out.println(response.getTotalPages());

                if (!response.getStatus().equals("ok")) {
                    throw new StatusNotOkException("FAILED: Status of response did not show OK");
                }

                sendArticles(response.getArticles());

                if (page >= response.getTotalPages()) {
                    hasUnseenPages = false;
                } else {
                    page++;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void sendArticles(List<Article> articles) {
        kafkaEndpointService.sendArticles(articles);
    }
}
