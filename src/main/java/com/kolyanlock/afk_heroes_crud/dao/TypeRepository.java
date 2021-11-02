package com.kolyanlock.afk_heroes_crud.dao;

import com.kolyanlock.afk_heroes_crud.entity.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TypeRepository extends JpaRepository<Type, String> {
    Page<Type> findByType(String type, Pageable pageable);

    @Query(value = "update types set type=?, description=? where type=?", nativeQuery = true)
    void updateQuery(String newType, String newDescription, String oldType);
}
