package com.kolyanlock.afk_heroes_crud.dto.heroclass;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kolyanlock.afk_heroes_crud.dto.hero.HeroForListDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class HeroClassWithHeroListDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String title;

    private String description;

    private List<HeroForListDTO> heroList;
}
