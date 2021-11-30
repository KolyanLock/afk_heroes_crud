package com.kolyanlock.afk_heroes_crud.exception;

public class FractionNotFoundException extends EntityNotFoundException{
    public FractionNotFoundException(String title) {
        super("Fraction with tittle " + title + " not found!");
    }
}
