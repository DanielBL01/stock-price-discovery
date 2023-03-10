package com.daniel.projects.dataproducermicroservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Article {

    @JsonProperty("title")
    private String title;
    // Usually, the latest headline posts don't include the author so not making it required
    @JsonProperty("author")
    private String author;
    @JsonProperty("summary")
    private String summary;
    @JsonProperty("link")
    private String link;
    @JsonProperty("published_date")
    private String publishedDate;

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getSummary() {
        return summary;
    }

    public String getLink() {
        return link;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public boolean hasAllRequiredFields() {
        return title != null && !title.isEmpty() &&
                summary != null && !summary.isEmpty() &&
                link != null && !link.isEmpty() &&
                publishedDate != null && !publishedDate.isEmpty();
    }
}
