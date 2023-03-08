package com.daniel.projects.datastreamingmicroservice.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.Optional;

@Entity
@Table(name = "news")
public class News {

    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "title", nullable = false, unique = true)
    private String title;
    @Column(name = "author")
    private String author;
    @Column(name = "summary", nullable = false, unique = true)
    private String summary;
    @Column(name = "url", nullable = false, unique = true)
    private String url;
    @Column(name = "labels", nullable = false)
    private List<Integer> labels;

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Optional<String> getAuthor() {
        if (author != null && author.isEmpty()) {
            return Optional.of(author);
        }
        return Optional.empty();
    }

    public String getSummary() {
        return summary;
    }

    public String getUrl() {
        return url;
    }

    public List<Integer> getLabels() {
        return labels;
    }

    public boolean hasAllRequiredFields() {
        return title != null && !title.isEmpty() &&
                summary != null && !summary.isEmpty() &&
                url != null && !url.isEmpty() &&
                labels != null && !labels.isEmpty();
    }
}
