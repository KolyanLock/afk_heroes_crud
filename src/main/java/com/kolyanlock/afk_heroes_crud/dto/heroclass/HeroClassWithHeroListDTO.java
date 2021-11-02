package com.kolyanlock.afk_heroes_crud.dto.heroclass;

import com.kolyanlock.afk_heroes_crud.dto.hero.HeroForListDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class HeroClassWithHeroListDTO {
    private String title;

    private String description;

    private List<HeroForListDTO> heroList;
}
