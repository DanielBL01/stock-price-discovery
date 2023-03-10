package com.daniel.projects.dataproducermicroservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;

@Configuration
@PropertySource("classpath:env.properties")
public class WebClientConfig {

    @Value("${api.key}")
    private String API_KEY;
    private final String BASE_URL_GET_NEWS = "https://api.newscatcherapi.com/v2";
    private final String BASE_URL_POST_ARTICLES = "http://localhost:8080";

    @Bean(name = "webGetNewsClient")
    public WebClient webGetNewsClient() {
        HttpClient httpClient = HttpClient.create()
                .responseTimeout(Duration.ofSeconds(30));

        return WebClient.builder()
                .baseUrl(BASE_URL_GET_NEWS)
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader("x-api-key", API_KEY)
                .build();
    }

    @Bean(name = "webPostArticlesClient")
    public WebClient webPostArticlesClient() {
        return WebClient.builder()
                .baseUrl(BASE_URL_POST_ARTICLES)
                .build();
    }
}
