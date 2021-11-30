package com.kolyanlock.afk_heroes_crud.exception;

public class HeroClassNotFoundException extends EntityNotFoundException {
    public HeroClassNotFoundException(String title) {
        super("Class with tittle " + title + " not found!");
    }
}
