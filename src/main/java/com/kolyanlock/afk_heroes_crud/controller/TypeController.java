package com.kolyanlock.afk_heroes_crud.controller;

import com.kolyanlock.afk_heroes_crud.dto.type.TypeDTO;
import com.kolyanlock.afk_heroes_crud.dto.type.TypeWithHeroListDTO;
import com.kolyanlock.afk_heroes_crud.service.TypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TypeController {
    private final TypeService typeService;


    @GetMapping("/types")
    public List<TypeDTO> getAllType(Sort sort) {
        return typeService.getAllTypes(sort);
    }

    @GetMapping("/types/{type}")
    public Page<TypeWithHeroListDTO> getType(@PathVariable String type, @PageableDefault Pageable pageable) {
        return typeService.getType(type, pageable);
    }

    @PostMapping("/types/new")
    public TypeDTO addNewType(@RequestBody TypeDTO typeDTO) {
        return typeService.addNewType(typeDTO);
    }

    @PutMapping("/types/{oldType}")
    public Page<TypeWithHeroListDTO> updateType(@PathVariable String oldType,
                                                @RequestBody TypeDTO typeDTO,
                                                Pageable pageable) {
        return typeService.updateType(oldType, typeDTO, pageable);
    }
    @DeleteMapping("/types/{type}")
    public String deleteType(@PathVariable String type) {
        return typeService.deleteType(type);
    }
}
