package com.kolyanlock.afk_heroes_crud.dao.Fraction;

public interface FractionCustomRepository {
    void update(String newTitle, String newDescription, String oldTitle);
}
