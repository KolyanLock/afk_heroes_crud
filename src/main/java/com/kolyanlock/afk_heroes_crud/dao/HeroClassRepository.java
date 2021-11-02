package com.kolyanlock.afk_heroes_crud.dao;

import com.kolyanlock.afk_heroes_crud.entity.HeroClass;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface HeroClassRepository extends JpaRepository<HeroClass, String> {
    Page<HeroClass> findByTitle(String title, Pageable pageable);

    @Query(value = "update classes set title=?, description=? where title=?", nativeQuery = true)
    void updateQuery(String newTitle, String newDescription, String oldTitle);
}
