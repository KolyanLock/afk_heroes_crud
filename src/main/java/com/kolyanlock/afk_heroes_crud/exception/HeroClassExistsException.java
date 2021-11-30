package com.kolyanlock.afk_heroes_crud.exception;

public class HeroClassExistsException extends EntityExistsException {
    public HeroClassExistsException(String title) {
        super("Class with title " + title + " already exists!");
    }
}
