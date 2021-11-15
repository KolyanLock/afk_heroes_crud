package com.kolyanlock.afk_heroes_crud.service;

import com.kolyanlock.afk_heroes_crud.dto.heroClass.HeroClassDTO;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface HeroClassService {
    List<HeroClassDTO> getAllHeroClasses(Sort sort);

    HeroClassDTO getHeroClass(String title);

    HeroClassDTO addNewHeroClass(HeroClassDTO heroClassDTO);

    HeroClassDTO updateHeroClass(String oldTitle, HeroClassDTO heroClassDTO);

    String deleteHeroClass(String title);
}
