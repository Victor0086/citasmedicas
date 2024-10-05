package com.citasmedicas.citasmedicas.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)


public class CitasNotFoundException extends RuntimeException {

    public CitasNotFoundException(String message){
        super(message);
    }
    
}
