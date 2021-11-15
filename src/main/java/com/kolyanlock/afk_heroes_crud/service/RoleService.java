package com.kolyanlock.afk_heroes_crud.service;

import com.kolyanlock.afk_heroes_crud.dto.role.RoleDTO;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface RoleService {
    List<RoleDTO> getAllRoles(Sort sort);

    RoleDTO getRole(String title);

    RoleDTO addNewRole(RoleDTO roleDTO);

    RoleDTO updateRole(String oldTitle, RoleDTO roleDTO);

    String deleteRole(String title);
}
