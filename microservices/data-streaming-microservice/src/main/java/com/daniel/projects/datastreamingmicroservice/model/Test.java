package com.daniel.projects.datastreamingmicroservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "test_topic")
public class Test {

    @Id
    @GeneratedValue
    @JsonProperty
    private Long id;

    @JsonProperty
    private String message;

    public String getMessage() {
        return message;
    }
}
