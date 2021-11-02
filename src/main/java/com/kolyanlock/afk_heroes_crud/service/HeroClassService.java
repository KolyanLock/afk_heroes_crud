package com.kolyanlock.afk_heroes_crud.service;

import com.kolyanlock.afk_heroes_crud.dto.heroclass.HeroClassDTO;
import com.kolyanlock.afk_heroes_crud.dto.heroclass.HeroClassWithHeroListDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface HeroClassService {
    List<HeroClassDTO> getAllHeroClasses(Sort sort);

    Page<HeroClassWithHeroListDTO> getHeroClass(String title, Pageable pageable);

    HeroClassDTO addNewHeroClass(HeroClassDTO heroClassDTO);

    Page<HeroClassWithHeroListDTO> updateHeroClass(String oldTitle, HeroClassDTO heroClassDTO, Pageable pageable);

    String deleteHeroClass(String title);
}
