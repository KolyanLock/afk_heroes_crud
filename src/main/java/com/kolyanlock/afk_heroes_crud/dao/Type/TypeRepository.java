package com.kolyanlock.afk_heroes_crud.dao.Type;

import com.kolyanlock.afk_heroes_crud.entity.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface TypeRepository extends JpaRepository<Type, String>, TypeCustomRepository {
    @Modifying
    @Transactional
    @Query(value = "update Type set type=?1, description=?2 where type=?3")
    void updateQuery(String newType, String newDescription, String oldType);
}
