package com.kolyanlock.afk_heroes_crud.service;

import com.kolyanlock.afk_heroes_crud.dto.hero.HeroForListDTO;
import com.kolyanlock.afk_heroes_crud.dto.hero.HeroMainDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface HeroService {
    Page<HeroForListDTO> getAllHeroes(Pageable pageable);
    HeroMainDTO getHeroById(int id);
    HeroMainDTO addNewHero(HeroMainDTO heroMainDTO);
    HeroMainDTO updateHero(HeroMainDTO heroMainDTO);
    String deleteHero(int id);
}
