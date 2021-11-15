package com.kolyanlock.afk_heroes_crud.dao;

import com.kolyanlock.afk_heroes_crud.entity.Fraction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface FractionRepository extends JpaRepository<Fraction, String> {
    @Modifying
    @Transactional
    @Query("update Fraction  set title=?1, description=?2 where title=?3")
    void updateQuery(String newTitle, String newDescription, String oldTitle);
}
