package com.kolyanlock.afk_heroes_crud.dto.hero;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class HeroDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private int id;

    @NotBlank
    @Size(max = 30)
    private String name;

    @NotBlank
    @Size(max = 50)
    private String title;

    @Size(max = 20)
    private String gender;

    @NotBlank
    @Size(max = 20)
    private String ultimate;

    @NotBlank
    @Size(max = 20)
    private String fraction;

    @NotBlank
    @Size(max = 20)
    private String type;

    @NotBlank
    @Size(max = 20)
    private String heroClass;

    @NotBlank
    @Size(max = 20)
    private String primaryRole;

    private String background;
}
