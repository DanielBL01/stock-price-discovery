package com.daniel.projects.datastreamingmicroservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "news")
public class News {

    @Id
    @GeneratedValue
    private Long id;
    @JsonProperty
    private String title;
    @JsonProperty
    private String summary;
    @JsonProperty
    private String link;
    @JsonProperty
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
}
