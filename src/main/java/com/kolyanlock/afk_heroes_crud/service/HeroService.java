package com.kolyanlock.afk_heroes_crud.service;

import com.kolyanlock.afk_heroes_crud.dto.hero.HeroDTO;
import com.kolyanlock.afk_heroes_crud.dto.hero.HeroForListDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface HeroService {
    Page<HeroForListDTO> getAllHeroes(Pageable pageable);

    HeroDTO getHeroById(int id);

    HeroDTO addNewHero(HeroDTO heroDTO);

    HeroDTO updateHero(HeroDTO heroDTO);

    String deleteHero(int id);

    Page<HeroDTO> findAllByFraction(String title, Pageable pageable);

    Page<HeroDTO> findAllByHeroClass(String heroClass, Pageable pageable);

    Page<HeroDTO> findAllByRole(String role, Pageable pageable);

    Page<HeroDTO> findAllByType(String type, Pageable pageable);
}
