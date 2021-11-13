package com.kolyanlock.afk_heroes_crud.service;

import com.kolyanlock.afk_heroes_crud.dao.RoleRepository;
import com.kolyanlock.afk_heroes_crud.dto.role.RoleDTO;
import com.kolyanlock.afk_heroes_crud.dto.role.RoleWithHeroListDTO;
import com.kolyanlock.afk_heroes_crud.entity.Role;
import com.kolyanlock.afk_heroes_crud.exception.EntityExistsException;
import com.kolyanlock.afk_heroes_crud.exception.EntityNotFoundException;
import com.kolyanlock.afk_heroes_crud.mappers.RoleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
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
        Page<Role> rolePage = roleRepository.findByTitle(title, pageable);
        if (rolePage.isEmpty())
            throw new EntityNotFoundException("Role with tittle " + title + " not found!");
        return roleRepository.findByTitle(title, pageable).map(RoleMapper.INSTANCE::toRoleWithHeroListDTO);
    }

    @Override
    public RoleDTO addNewRole(RoleDTO roleDTO) {
        String id = roleDTO.getTitle();
        if (roleRepository.findById(id).isPresent()) {
            throw new EntityExistsException("Role with title " + id + " already exists!");
        }
        Role newRole = RoleMapper.INSTANCE.toRoleEntity(roleDTO);
        roleRepository.save(newRole);
        return roleDTO;
    }

    @Override
    public Page<RoleWithHeroListDTO> updateRole(String oldTitle, RoleDTO roleDTO, Pageable pageable) {
        if (!roleRepository.findById(oldTitle).isPresent()) {
            throw new EntityNotFoundException("Role with tittle " + oldTitle + " not found!");
        }
        String newTitle = roleDTO.getTitle();
        String newDescription = roleDTO.getDescription();
        try {
            roleRepository.updateQuery(newTitle, newDescription, oldTitle);
        } catch (DataIntegrityViolationException e) {
            throw new EntityExistsException("Class with title " + newTitle + " already exists!");
        }
        Page<RoleWithHeroListDTO> roleWithHeroListDTOPage = getRole(newTitle, pageable);
        if (roleWithHeroListDTOPage.isEmpty()) {
            throw new EntityNotFoundException("Role with tittle " + oldTitle + " not found!");
        }
        return getRole(newTitle, pageable);
    }

    @Override
    public String deleteRole(String title) {
        try {
            roleRepository.deleteById(title);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException("Role with tittle " + title + " not found!");
        }
        return "Role with title " + title + " was deleted";
    }
}
