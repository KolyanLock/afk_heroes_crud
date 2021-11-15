package com.kolyanlock.afk_heroes_crud.mappers;

import com.kolyanlock.afk_heroes_crud.dto.hero.HeroDTO;
import com.kolyanlock.afk_heroes_crud.dto.hero.HeroForListDTO;
import com.kolyanlock.afk_heroes_crud.entity.Hero;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;


@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface HeroMapper {
    HeroMapper INSTANCE = Mappers.getMapper( HeroMapper.class );

    HeroForListDTO toHeroForListDTO(Hero hero);


    @Mapping(target = "gender", source = "gender", defaultValue = "Not specified")
    @Mapping(target = "background", source = "background", defaultValue = "Lore has not yet been written.")
    HeroDTO toHeroDTO(Hero hero);

    Hero toHeroEntity(HeroDTO heroDTO);
}
