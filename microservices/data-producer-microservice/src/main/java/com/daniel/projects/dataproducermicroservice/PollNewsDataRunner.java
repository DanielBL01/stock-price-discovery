package com.daniel.projects.dataproducermicroservice;

import com.daniel.projects.dataproducermicroservice.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class PollNewsDataRunner implements CommandLineRunner {

    private final RequestService requestService;

    @Autowired
    public PollNewsDataRunner(RequestService requestService) {
        this.requestService = requestService;
    }

    @Override
    public void run(String... args) {
        requestService.pollNewsData();
    }
}
