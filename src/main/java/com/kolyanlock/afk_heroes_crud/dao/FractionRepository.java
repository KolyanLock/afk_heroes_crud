package com.kolyanlock.afk_heroes_crud.dao;

import com.kolyanlock.afk_heroes_crud.entity.Fraction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Optional;

public interface FractionRepository extends JpaRepository<Fraction, String> {
    Page<Fraction> getByTitle(String title, Pageable pageable);

    @Modifying
    @Transactional
    @Query("update Fraction  set title=?1, description=?2 where title=?3")
    void updateQuery(String newTitle, String newDescription, String oldTitle);

    //        @EntityGraph(attributePaths = {"heroList", "heroList.id"})
//    List<Fraction> findAllByHeroId(int id);
//    @Query("select f from Fraction f where f>?1")
//    List<Fraction> findAllByHeroId(int id);
}
