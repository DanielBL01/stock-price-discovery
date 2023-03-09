package com.daniel.projects.dataproducermicroservice;

import com.daniel.projects.dataproducermicroservice.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class RunGetData implements CommandLineRunner {

    private final RequestService requestService;

    @Autowired
    public RunGetData (RequestService requestService) {
        this.requestService = requestService;
    }

    @Override
    public void run(String... args) throws Exception {
        Mono<String> data = requestService.getData();
        System.out.println("Got the data without any error!");
    }
}
