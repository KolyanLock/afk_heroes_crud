package com.kolyanlock.afk_heroes_crud.dao;

import com.kolyanlock.afk_heroes_crud.entity.Hero;
import org.springframework.data.jpa.repository.JpaRepository;


public interface HeroRepository extends JpaRepository<Hero, Integer> {
}
