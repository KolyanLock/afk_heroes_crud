package com.kolyanlock.afk_heroes_crud.mappers;

import com.kolyanlock.afk_heroes_crud.dto.hero.HeroForListDTO;
import com.kolyanlock.afk_heroes_crud.dto.hero.HeroMainDTO;
import com.kolyanlock.afk_heroes_crud.entity.Hero;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface HeroMapper {
    HeroMapper INSTANCE = Mappers.getMapper( HeroMapper.class );

    HeroForListDTO toHeroForListDTO(Hero hero);


    @Mapping(target = "background", source = "background", defaultValue = "Lore has not yet been written.")
    HeroMainDTO toHeroMainDTO(Hero hero);

    Hero toHeroEntity(HeroMainDTO heroMainDTO);

    List<HeroForListDTO> toListHeroForListDTO(List<Hero> heroList);
}
