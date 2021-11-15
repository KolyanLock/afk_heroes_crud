package com.kolyanlock.afk_heroes_crud.service;

import com.kolyanlock.afk_heroes_crud.dto.fraction.FractionDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface FractionService {
    Page<FractionDTO> getAllFactions(Pageable pageable);

    FractionDTO getFraction(String title);

    FractionDTO addNewFraction(FractionDTO fractionDTO);

    FractionDTO updateFraction(String oldTitle, FractionDTO fractionDTO);

    String deleteFraction(String title);

}
