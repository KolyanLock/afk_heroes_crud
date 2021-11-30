package com.kolyanlock.afk_heroes_crud.exception;

public class RoleExistsException extends EntityExistsException {
    public RoleExistsException(String title) {
        super("Role with title " + title + " already exists!");
    }
}
