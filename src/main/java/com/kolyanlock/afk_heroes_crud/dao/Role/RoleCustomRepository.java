package com.kolyanlock.afk_heroes_crud.dao.Role;

public interface RoleCustomRepository {
    void update(String newTitle, String newDescription, String oldTitle);
}
