package com.kolyanlock.afk_heroes_crud.controller;

import com.kolyanlock.afk_heroes_crud.dto.type.TypeDTO;
import com.kolyanlock.afk_heroes_crud.service.TypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TypeController {
    private final TypeService typeService;


    @GetMapping("/types")
    public List<TypeDTO> getAllTypes(Sort sort) {
        return typeService.getAllTypes(sort);
    }

    @GetMapping("/types/{type}")
    public TypeDTO getType(@PathVariable String type) {
        return typeService.getType(type);
    }

    @PostMapping("/types/new")
    public TypeDTO addNewType(@Valid @RequestBody TypeDTO typeDTO) {
        return typeService.addNewType(typeDTO);
    }

    @PutMapping("/types/{oldTitle}")
    public TypeDTO updateType(@PathVariable String oldTitle,
                              @Valid @RequestBody TypeDTO typeDTO) {
        return typeService.updateType(oldTitle, typeDTO);
    }

    @DeleteMapping("/types/{type}")
    public String deleteType(@PathVariable String type) {
        return typeService.deleteType(type);
    }
}
