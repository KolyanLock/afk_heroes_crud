package com.kolyanlock.afk_heroes_crud.service;

import com.kolyanlock.afk_heroes_crud.dao.RoleRepository;
import com.kolyanlock.afk_heroes_crud.dto.role.RoleDTO;
import com.kolyanlock.afk_heroes_crud.dto.role.RoleWithHeroListDTO;
import com.kolyanlock.afk_heroes_crud.entity.Role;
import com.kolyanlock.afk_heroes_crud.mappers.RoleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpls implements RoleService {
    private final RoleRepository roleRepository;

    @Override
    public List<RoleDTO> getAllRoles(Sort sort) {
        return RoleMapper.INSTANCE.toListRoleDTO(roleRepository.findAll(sort));
    }

    @Override
    public Page<RoleWithHeroListDTO> getRole(String title, Pageable pageable) {
        return roleRepository.findByTitle(title, pageable).map(RoleMapper.INSTANCE::toRoleWithHeroListDTO);
    }

    @Override
    public RoleDTO addNewRole(RoleDTO roleDTO) {
        String id = roleDTO.getTitle();
        if (roleRepository.findById(id).isPresent()) {
            roleDTO.setDescription("This Role already exists!");
            //"Role with title " + id + " already exists!";
        }
        Role newRole = RoleMapper.INSTANCE.toRoleEntity(roleDTO);
        roleRepository.save(newRole);
        return roleDTO;
    }

    @Override
    public Page<RoleWithHeroListDTO> updateRole(String oldTitle, RoleDTO roleDTO, Pageable pageable) {
        String newTitle = roleDTO.getTitle();
        String newDescription = roleDTO.getDescription();
        try {
            roleRepository.updateQuery(newTitle, newDescription, oldTitle);
        } catch (Exception e) {
            System.out.println("Role update!");
        }
        return getRole(newTitle, pageable);
    }

    @Override
    public String deleteRole(String title) {
        roleRepository.deleteById(title);
        return "Role with title " + title + " was deleted";
    }
}
