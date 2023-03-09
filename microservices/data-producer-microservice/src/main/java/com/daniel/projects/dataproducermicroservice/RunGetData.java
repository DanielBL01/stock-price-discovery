package com.daniel.projects.dataproducermicroservice;

import com.daniel.projects.dataproducermicroservice.service.RequestService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Iterator;
import java.util.Map;

@Component
public class RunGetData implements CommandLineRunner {

    private final RequestService requestService;

    @Autowired
    public RunGetData (RequestService requestService) {
        this.requestService = requestService;
    }

    @Override
    public void run(String... args) {
        Mono<String> data = requestService.getData();
        data.subscribe(response -> {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                JsonNode jsonNode = objectMapper.readTree(response);
                Iterator<Map.Entry<String, JsonNode>> fieldsIterator = jsonNode.fields();
                while (fieldsIterator.hasNext()) {
                    Map.Entry<String, JsonNode> field = fieldsIterator.next();
                    String fieldName = field.getKey();
                    JsonNode fieldValue = field.getValue();
                    System.out.println("Field name: " + fieldName);
                    System.out.println("Field value: " + fieldValue);
                }
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }, error -> {
            System.out.println("Error: " + error.getMessage());
        });
    }
}
