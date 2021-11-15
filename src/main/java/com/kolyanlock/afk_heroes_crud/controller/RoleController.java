package com.kolyanlock.afk_heroes_crud.controller;

import com.kolyanlock.afk_heroes_crud.dto.role.RoleDTO;
import com.kolyanlock.afk_heroes_crud.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class RoleController {
    private final RoleService roleService;


    @GetMapping("/roles")
    public List<RoleDTO> getAllRoles(Sort sort) {
        return roleService.getAllRoles(sort);
    }

    @GetMapping("/roles/{title}")
    public RoleDTO getRole(@PathVariable String title) {
        return roleService.getRole(title);
    }

    @PostMapping("/roles/new")
    public RoleDTO addNewRole(@Valid @RequestBody RoleDTO roleDTO) {
        return roleService.addNewRole(roleDTO);
    }

    @PutMapping("/roles/{oldTitle}")
    public RoleDTO updateRole(@PathVariable String oldTitle,
                              @Valid @RequestBody RoleDTO roleDTO) {
        return roleService.updateRole(oldTitle, roleDTO);
    }

    @DeleteMapping("/roles/{title}")
    public String deleteRole(@PathVariable String title) {
        return roleService.deleteRole(title);
    }
}
