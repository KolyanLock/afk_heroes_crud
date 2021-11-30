package com.kolyanlock.afk_heroes_crud.exception;

public class TypeNotFoundException extends EntityNotFoundException{
    public TypeNotFoundException(String type) {
        super("Type " + type + " not found!");
    }
}
