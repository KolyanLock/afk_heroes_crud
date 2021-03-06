package com.kolyanlock.afk_heroes_crud.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "classes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HeroClass {

    @Id
    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;
}
