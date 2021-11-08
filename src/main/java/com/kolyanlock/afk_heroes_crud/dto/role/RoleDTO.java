package com.kolyanlock.afk_heroes_crud.dto.role;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class RoleDTO {

    @NotBlank
    @Size(max = 20)
    private String title;

    private String description;
}
