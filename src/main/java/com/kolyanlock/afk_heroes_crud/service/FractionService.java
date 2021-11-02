package com.kolyanlock.afk_heroes_crud.service;

import com.kolyanlock.afk_heroes_crud.dto.fraction.FractionDTO;
import com.kolyanlock.afk_heroes_crud.dto.fraction.FractionWithHeroListDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface FractionService {
    List<FractionDTO> getAllFactions(Sort sort);

    Page<FractionWithHeroListDTO> getFraction(String title, Pageable pageable);

    FractionDTO addNewFraction(FractionDTO fractionDTO);

    Page<FractionWithHeroListDTO> updateFraction(String oldTitle, FractionDTO fractionDTO, Pageable pageable);

    String deleteFraction(String title);
}
