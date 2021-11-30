package com.kolyanlock.afk_heroes_crud.service;

import com.kolyanlock.afk_heroes_crud.dao.RoleRepository;
import com.kolyanlock.afk_heroes_crud.dto.role.RoleDTO;
import com.kolyanlock.afk_heroes_crud.entity.Role;
import com.kolyanlock.afk_heroes_crud.exception.RoleExistsException;
import com.kolyanlock.afk_heroes_crud.exception.RoleNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.kolyanlock.afk_heroes_crud.mappers.RoleMapper.ROLE_MAPPER;

@Service
@RequiredArgsConstructor
public class RoleServiceImpls implements RoleService {
    private final RoleRepository roleRepository;

    @Override
    public List<RoleDTO> getAllRoles(Sort sort) {
        return ROLE_MAPPER.toListRoleDTO(roleRepository.findAll(sort));
    }

    @Override
    public RoleDTO getRole(String title) {
        Optional<Role> roleDTOOptional = roleRepository.findById(title);
        if (!roleDTOOptional.isPresent())
            throw new RoleNotFoundException(title);
        return ROLE_MAPPER.toRoleDTO(roleDTOOptional.get());
    }

    @Override
    public RoleDTO addNewRole(RoleDTO roleDTO) {
        String id = roleDTO.getTitle();
        if (roleRepository.findById(id).isPresent()){
            throw new RoleExistsException(id);
        }
        Role newRole = ROLE_MAPPER.toRoleEntity(roleDTO);
        roleRepository.save(newRole);
        return roleDTO;
    }

    @Override
    public RoleDTO updateRole(String oldTitle, RoleDTO roleDTO) {
        if (!roleRepository.findById(oldTitle).isPresent()) {
            throw new RoleNotFoundException(oldTitle);
        }
        String newTitle = roleDTO.getTitle();
        String newDescription = roleDTO.getDescription();
        try {
            roleRepository.updateQuery(newTitle, newDescription, oldTitle);
        } catch (DataIntegrityViolationException e) {
            throw new RoleExistsException(newTitle);
        }
        Optional<Role> optionalRole = roleRepository.findById(newTitle);
        if (!optionalRole.isPresent()) {
            throw new RoleNotFoundException(oldTitle);
        }
        return ROLE_MAPPER.toRoleDTO(optionalRole.get());
    }

    @Override
    public String deleteRole(String title) {
        try {
            roleRepository.deleteById(title);
        } catch (EmptyResultDataAccessException e) {
            throw new RoleNotFoundException(title);
        }
        return "Role with title " + title + " was deleted";
    }
}
