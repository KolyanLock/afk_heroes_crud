package com.kolyanlock.afk_heroes_crud.dto.fraction;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class FractionDTO {

    @NotBlank
    @Size(max = 20)
    private String title;

    private String description;
}
