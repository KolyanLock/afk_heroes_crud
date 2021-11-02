package com.kolyanlock.afk_heroes_crud.mappers;

import com.kolyanlock.afk_heroes_crud.dto.fraction.FractionDTO;
import com.kolyanlock.afk_heroes_crud.dto.fraction.FractionWithHeroListDTO;
import com.kolyanlock.afk_heroes_crud.entity.Fraction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface FractionMapper {
    FractionMapper INSTANCE = Mappers.getMapper( FractionMapper.class );

    FractionDTO toFractionDTO(Fraction fraction);

    @Mapping(target = "heroList", ignore = true)
    Fraction toFractionEntity(FractionDTO fractionDTO);

    @Mapping(target = "description", source = "description", defaultValue = "There is no description yet.")
    List<FractionDTO> toListFractionDTO(List<Fraction> fractionList);

    FractionWithHeroListDTO toFractionWithHeroListDTO(Fraction fraction);
}
