package com.kolyanlock.afk_heroes_crud.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "types")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Type {

    @Id
    private String type;

    private String description;

    @OneToMany(mappedBy = "type")
    @OrderBy("name")
    private List<Hero> heroList;

}
