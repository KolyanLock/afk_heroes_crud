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

    @Id
    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;
}
