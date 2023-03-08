package com.daniel.projects.datastreamingmicroservice.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "news")
public class News {

    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "summary", nullable = false)
    private String summary;
    @Column(name = "link", nullable = false, unique = true)
    private String link;
    @Column(name = "labels", nullable = false)
    private List<Integer> labels;

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getSummary() {
        return summary;
    }

    public String getLink() {
        return link;
    }

    public List<Integer> getLabels() {
        return labels;
    }

    public boolean hasAllFields() {
        return title != null && !title.isEmpty() &&
                summary != null && !summary.isEmpty() &&
                link != null && !link.isEmpty() &&
                labels != null && !labels.isEmpty();
    }
}
