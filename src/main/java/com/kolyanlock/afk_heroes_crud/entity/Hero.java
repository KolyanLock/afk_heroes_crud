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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @OrderColumn
    private String name;

    private String title;

    private String gender;

    private String ultimate;

    private String fraction;

    private String type;

    @Column(name = "class")
    private String heroClass;

    @Column(name = "primary_role")
    private String primaryRole;

    private String background;
}
