package com.kolyanlock.afk_heroes_crud.dto.type;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class TypeDTO {

    @NotBlank
    @Size(max = 20)
    private String type;

    private String description;
}
