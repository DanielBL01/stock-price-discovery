package com.daniel.projects.dataproducermicroservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class NewsResponse {

    @JsonProperty("status")
    private String status;
    @JsonProperty("total_hits")
    private int totalHits;
    @JsonProperty("page")
    private int page;
    @JsonProperty("total_pages")
    private int totalPages;
    @JsonProperty("page_size")
    private int pageSize;
    @JsonProperty("articles")
    private List<Article> articles;

    public String getStatus() {
        return status;
    }

    public int getTotalHits() {
        return totalHits;
    }

    public int getPage() {
        return page;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public int getPageSize() {
        return pageSize;
    }

    public List<Article> getArticles() {
        return articles;
    }
}
