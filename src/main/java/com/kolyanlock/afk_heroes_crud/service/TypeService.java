package com.kolyanlock.afk_heroes_crud.service;

import com.kolyanlock.afk_heroes_crud.dto.type.TypeDTO;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface TypeService {
    List<TypeDTO> getAllTypes(Sort sort);

    TypeDTO getType(String type);

    TypeDTO addNewType(TypeDTO typeDTO);

    TypeDTO updateType(String oldType, TypeDTO typeDTO);

    String deleteType(String type);
}
