package com.kolyanlock.afk_heroes_crud.mappers;

import com.kolyanlock.afk_heroes_crud.dto.role.RoleDTO;
import com.kolyanlock.afk_heroes_crud.dto.role.RoleWithHeroListDTO;
import com.kolyanlock.afk_heroes_crud.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface RoleMapper {
    RoleMapper INSTANCE = Mappers.getMapper( RoleMapper.class );

    RoleDTO toRoleDTO(Role role);

    @Mapping(target = "heroList", ignore = true)
    Role toRoleEntity(RoleDTO roleDTO);

    @Mapping(target = "description", source = "description", defaultValue = "There is no description yet.")
    List<RoleDTO> toListRoleDTO(List<Role> roleList);

    RoleWithHeroListDTO toRoleWithHeroListDTO(Role role);
}
