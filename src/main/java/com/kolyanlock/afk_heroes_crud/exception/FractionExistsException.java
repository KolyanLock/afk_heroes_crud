package com.kolyanlock.afk_heroes_crud.exception;

public class FractionExistsException extends EntityExistsException {
    public FractionExistsException(String title) {
        super("Fraction with title " + title + " already exists!");
    }
}
