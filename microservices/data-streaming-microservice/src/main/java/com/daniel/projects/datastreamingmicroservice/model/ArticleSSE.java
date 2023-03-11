package com.daniel.projects.datastreamingmicroservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;

import java.util.Optional;

public class ArticleSSE {

    @Column(name = "title", nullable = false, unique = false)
    @JsonProperty("title")
    private String title;
    @Column(name = "author", nullable = true, unique = false)
    @JsonProperty("author")
    private String author;
    @Column(name = "summary", nullable = false, unique = false, length = 1000)
    @JsonProperty("summary")
    private String summary;
    @Column(name = "link", nullable = false, unique = false)
    @JsonProperty("link")
    private String link;
    @Column(name = "published_date", nullable = false, unique = false)
    @JsonProperty("published_date")
    private String publishedDate;

    public ArticleSSE(String title, Optional<String> author, String summary, String link, String publishedDate) {
        this.title = title;
        this.summary = summary;
        this.link = link;
        this.publishedDate = publishedDate;
        if (author.isPresent()) {
            this.author = author.get();
        } else {
            this.author = null;
        }
    }

    public String getTitle() {
        return title;
    }

    public Optional<String> getAuthor() {
        if (author != null && !author.isEmpty()) {
            return Optional.of(author);
        }
        return Optional.empty();
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
