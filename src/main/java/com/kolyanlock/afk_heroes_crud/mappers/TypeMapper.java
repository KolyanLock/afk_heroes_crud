package com.kolyanlock.afk_heroes_crud.mappers;


import com.kolyanlock.afk_heroes_crud.dto.type.TypeDTO;
import com.kolyanlock.afk_heroes_crud.dto.type.TypeWithHeroListDTO;
import com.kolyanlock.afk_heroes_crud.entity.Type;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface TypeMapper {
    TypeMapper INSTANCE = Mappers.getMapper( TypeMapper.class );

    TypeDTO toTypeDTO(Type type);

    @Mapping(target = "heroList", ignore = true)
    Type toTypeEntity(TypeDTO typeDTO);

    @Mapping(target = "description", source = "description", defaultValue = "There is no description yet.")
    List<TypeDTO> toListTypeDTO(List<Type> typeList);

    TypeWithHeroListDTO toTypeWithHeroListDTO(Type type);
}
