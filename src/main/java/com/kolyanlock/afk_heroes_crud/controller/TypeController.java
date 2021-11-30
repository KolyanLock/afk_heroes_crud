package com.kolyanlock.afk_heroes_crud.controller;

import com.kolyanlock.afk_heroes_crud.dto.type.TypeDTO;
import com.kolyanlock.afk_heroes_crud.service.TypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/types")
@RequiredArgsConstructor
public class TypeController {
    private final TypeService typeService;


    @GetMapping
    public List<TypeDTO> getAllTypes(Sort sort) {
        return typeService.getAllTypes(sort);
    }

    @GetMapping("/{type}")
    public TypeDTO getType(@PathVariable String type) {
        return typeService.getType(type);
    }

    @PostMapping("/new")
    public TypeDTO addNewType(@Valid @RequestBody TypeDTO typeDTO) {
        return typeService.addNewType(typeDTO);
    }

    @PutMapping("/{oldTitle}")
    public TypeDTO updateType(@PathVariable String oldTitle,
                              @Valid @RequestBody TypeDTO typeDTO) {
        return typeService.updateType(oldTitle, typeDTO);
    }

    @DeleteMapping("/{type}")
    public String deleteType(@PathVariable String type) {
        return typeService.deleteType(type);
    }
}
