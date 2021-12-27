package com.kolyanlock.afk_heroes_crud.dao.Type;

public interface TypeCustomRepository {
    void update(String newType, String newDescription, String oldType);
}
