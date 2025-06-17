package com.reinertisa.springdatajpamappings.one_to_one_uni.exception;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String message, Long id) {
        super(message + " " + id);
    }
}
