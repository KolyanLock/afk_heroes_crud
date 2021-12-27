package com.kolyanlock.afk_heroes_crud.dao.Hero;

import com.kolyanlock.afk_heroes_crud.entity.Hero;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface HeroRepository extends JpaRepository<Hero, Integer> {
    Page<Hero> findAllByFraction(String title, Pageable pageable);

    Page<Hero> findAllByHeroClass(String heroClass, Pageable pageable);

    Page<Hero> findAllByPrimaryRole(String role, Pageable pageable);

    Page<Hero> findAllByType(String type, Pageable pageable);
}
