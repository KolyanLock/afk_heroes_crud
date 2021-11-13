package com.kolyanlock.afk_heroes_crud.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

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

    @OneToMany
    @JoinColumn(name = "fraction")
    @OrderBy("name")
    private List<Hero> heroList;

}
