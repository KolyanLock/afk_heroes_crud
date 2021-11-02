package com.kolyanlock.afk_heroes_crud.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "classes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HeroClass {

    @Id
    private String title;

    private String description;

    @OneToMany(mappedBy = "heroClass")
    @OrderBy("name")
    private List<Hero> heroList;
}
