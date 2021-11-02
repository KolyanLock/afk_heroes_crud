package com.kolyanlock.afk_heroes_crud.dto.hero;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class HeroForListDTO {

//    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private int id;

    private String name;

    private String title;

    private String gender;

    private String ultimate;

    private String fraction;

    private String type;

    private String heroClass;

    private String primaryRole;
}
