package com.kolyanlock.afk_heroes_crud.dao;

import com.kolyanlock.afk_heroes_crud.dto.heroClass.HeroClassDTO;

public interface HeroClassCustomRepository {
    void update(String newTitle, String newDescription, String oldTitle);
}
