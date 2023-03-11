package com.daniel.projects.datastreamingmicroservice.deserializer;

import com.daniel.projects.datastreamingmicroservice.model.Article;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

public class CustomArticleDeserializer implements Deserializer<Article> {

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {

    }

    @Override
    public Article deserialize(String topic, byte[] data) {
        ObjectMapper mapper = new ObjectMapper();
        Article article = null;
        try {
            article = mapper.readValue(data, Article.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return article;
    }

    @Override
    public void close() {

    }
}
