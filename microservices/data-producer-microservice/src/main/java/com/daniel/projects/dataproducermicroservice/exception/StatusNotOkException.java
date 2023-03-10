package com.daniel.projects.dataproducermicroservice.exception;

public class StatusNotOkException extends Exception {
    public StatusNotOkException(String message) {
        super(message);
    }
}
