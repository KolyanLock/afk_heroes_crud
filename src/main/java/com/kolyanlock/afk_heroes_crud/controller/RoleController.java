package com.kolyanlock.afk_heroes_crud.controller;

import com.kolyanlock.afk_heroes_crud.dto.role.RoleDTO;
import com.kolyanlock.afk_heroes_crud.dto.role.RoleWithHeroListDTO;
import com.kolyanlock.afk_heroes_crud.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class RoleController {
    private final RoleService roleService;


    @GetMapping("/roles")
    public List<RoleDTO> getAllRole(Sort sort) {
        return roleService.getAllRoles(sort);
    }

    @GetMapping("/roles/{title}")
    public Page<RoleWithHeroListDTO> getRole(@PathVariable String title, @PageableDefault Pageable pageable) {
        return roleService.getRole(title, pageable);
    }

    @PostMapping("/roles/new")
    public RoleDTO addNewRole(@Valid @RequestBody RoleDTO roleDTO) {
        return roleService.addNewRole(roleDTO);
    }

    @PutMapping("/roles/{oldTitle}")
    public Page<RoleWithHeroListDTO> updateRole(@PathVariable String oldTitle,
                                                @Valid @RequestBody RoleDTO roleDTO,
                                                Pageable pageable) {
        return roleService.updateRole(oldTitle, roleDTO, pageable);
    }

    @DeleteMapping("/roles/{title}")
    public String deleteRole(@PathVariable String title) {
        return roleService.deleteRole(title);
    }
}
