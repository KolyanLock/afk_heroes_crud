package com.kolyanlock.afk_heroes_crud.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "factions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Fraction {

    @Id()
    private String title;

    private String description;
}
