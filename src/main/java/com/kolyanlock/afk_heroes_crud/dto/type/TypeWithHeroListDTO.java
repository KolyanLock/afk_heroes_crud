package com.kolyanlock.afk_heroes_crud.dto.type;

import com.kolyanlock.afk_heroes_crud.dto.hero.HeroForListDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TypeWithHeroListDTO {
    private String type;

    private String description;

    private List<HeroForListDTO> heroList;
}
