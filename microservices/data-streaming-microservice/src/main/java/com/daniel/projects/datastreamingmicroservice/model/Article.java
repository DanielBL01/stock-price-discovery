package com.daniel.projects.datastreamingmicroservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.Optional;

@Entity
@Table(name = "articles")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title", nullable = false)
    @JsonProperty("title")
    private String title;
    @Column(name = "author")
    @JsonProperty("author")
    private String author;
    @Column(name = "summary", nullable = false, length = 1000)
    @JsonProperty("summary")
    private String summary;
    @Column(name = "link", nullable = false, unique = true)
    @JsonProperty("link")
    private String link;
    @Column(name = "published_date", nullable = false)
    @JsonProperty("published_date")
    private String publishedDate;
    // Right now make the labels nullable. But in the future, all news articles should be labelled.
    // @Column(name = "labels")
    // private List<Integer> labels;

    public Long getId() {
        return id;
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
