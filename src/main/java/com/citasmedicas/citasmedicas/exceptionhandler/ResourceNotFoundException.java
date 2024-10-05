package com.citasmedicas.citasmedicas.exceptionhandler;

public class ResourceNotFoundException extends RuntimeException{
    
    public ResourceNotFoundException(String message) {
        super(message);
    }
    
}
