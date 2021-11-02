package com.kolyanlock.afk_heroes_crud.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "roles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Role {

    @Id
    private String title;

    private String description;

    @OneToMany(mappedBy = "primaryRole")
    @OrderBy("name")
    private List<Hero> heroList;

}
