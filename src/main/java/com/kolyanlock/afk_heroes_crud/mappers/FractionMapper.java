package com.kolyanlock.afk_heroes_crud.mappers;

import com.kolyanlock.afk_heroes_crud.dto.fraction.FractionDTO;
import com.kolyanlock.afk_heroes_crud.entity.Fraction;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface FractionMapper {
    FractionMapper FRACTION_MAPPER = Mappers.getMapper( FractionMapper.class );

    FractionDTO toFractionDTO(Fraction fraction);

    Fraction toFractionEntity(FractionDTO fractionDTO);
}
