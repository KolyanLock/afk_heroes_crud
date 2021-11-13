package com.kolyanlock.afk_heroes_crud.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class EntityExistsException extends ResponseStatusException {
    public EntityExistsException(String message){
        super(HttpStatus.BAD_REQUEST, message);
    }
}
