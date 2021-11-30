package com.kolyanlock.afk_heroes_crud.exception;

public class TypeExistsException extends EntityExistsException {
    public TypeExistsException(String type) {
        super("Type " + type + " already exists!");
    }
}
