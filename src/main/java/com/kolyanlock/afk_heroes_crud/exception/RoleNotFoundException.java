package com.kolyanlock.afk_heroes_crud.exception;

public class RoleNotFoundException extends EntityNotFoundException{
    public RoleNotFoundException(String title) {
        super("Role with tittle " + title + " not found!");
    }
}
