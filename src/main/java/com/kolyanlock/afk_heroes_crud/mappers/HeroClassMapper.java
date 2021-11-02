package com.kolyanlock.afk_heroes_crud.mappers;

import com.kolyanlock.afk_heroes_crud.dto.heroclass.HeroClassDTO;
import com.kolyanlock.afk_heroes_crud.dto.heroclass.HeroClassWithHeroListDTO;
import com.kolyanlock.afk_heroes_crud.entity.HeroClass;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface HeroClassMapper {
    HeroClassMapper INSTANCE = Mappers.getMapper( HeroClassMapper.class );

    HeroClassDTO toHeroClassDTO(HeroClass heroClass);

    @Mapping(target = "heroList", ignore = true)
    HeroClass toHeroClassEntity(HeroClassDTO heroClassDTO);

    @Mapping(target = "description", source = "description", defaultValue = "There is no description yet.")
    List<HeroClassDTO> toListHeroClassDTO(List<HeroClass> heroClassList);

    HeroClassWithHeroListDTO toHeroClassWithHeroListDTO(HeroClass heroClass);

}
