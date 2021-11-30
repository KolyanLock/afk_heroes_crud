package com.kolyanlock.afk_heroes_crud.entity;

import lombok.*;

import javax.persistence.*;


@Entity
@Table(name = "heroes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Hero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @OrderColumn(name = "name")
    private String name;

    @Column(name = "title")
    private String title;

    @Column(name = "gender")
    private String gender;

    @Column(name = "ultimate")
    private String ultimate;

    @Column(name = "fraction")
    private String fraction;

    @Column(name = "type")
    private String type;

    @Column(name = "class")
    private String heroClass;

    @Column(name = "primary_role")
    private String primaryRole;

    @Column(name = "background")
    private String background;
}
