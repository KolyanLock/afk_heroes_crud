package com.kolyanlock.afk_heroes_crud.exception;

public class HeroNotFoundException extends EntityNotFoundException {
    public HeroNotFoundException(int id) {
        super("Hero with tittle " + id + " not found!");
    }
}
