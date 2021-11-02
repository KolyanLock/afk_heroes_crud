package com.kolyanlock.afk_heroes_crud.service;

import com.kolyanlock.afk_heroes_crud.dto.role.RoleDTO;
import com.kolyanlock.afk_heroes_crud.dto.role.RoleWithHeroListDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface RoleService {
    List<RoleDTO> getAllRoles(Sort sort);

    Page<RoleWithHeroListDTO> getRole(String title, Pageable pageable);

    RoleDTO addNewRole(RoleDTO roleDTO);

    Page<RoleWithHeroListDTO> updateRole(String oldTitle, RoleDTO roleDTO, Pageable pageable);

    String deleteRole(String title);
}
