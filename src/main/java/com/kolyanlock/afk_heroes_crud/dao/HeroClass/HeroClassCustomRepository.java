package com.kolyanlock.afk_heroes_crud.dao.HeroClass;


public interface HeroClassCustomRepository {
    void update(String newTitle, String newDescription, String oldTitle);
}
