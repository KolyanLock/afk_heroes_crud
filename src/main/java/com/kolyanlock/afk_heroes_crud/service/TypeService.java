package com.kolyanlock.afk_heroes_crud.service;

import com.kolyanlock.afk_heroes_crud.dto.type.TypeDTO;
import com.kolyanlock.afk_heroes_crud.dto.type.TypeWithHeroListDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface TypeService {
    List<TypeDTO> getAllTypes(Sort sort);

    Page<TypeWithHeroListDTO> getType(String title, Pageable pageable);

    TypeDTO addNewType(TypeDTO typeDTO);

    Page<TypeWithHeroListDTO> updateType(String oldTitle, TypeDTO typeDTO, Pageable pageable);

    String deleteType(String title);
}
