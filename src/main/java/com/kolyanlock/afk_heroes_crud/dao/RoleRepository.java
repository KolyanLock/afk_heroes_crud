package com.kolyanlock.afk_heroes_crud.dao;

import com.kolyanlock.afk_heroes_crud.entity.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RoleRepository extends JpaRepository<Role, String > {
    Page<Role> findByTitle(String title, Pageable pageable);

    @Query(value = "update roles set title=?, description=? where title=?", nativeQuery = true)
    void updateQuery(String newTitle, String newDescription, String oldTitle);
}
