package com.kolyanlock.afk_heroes_crud.controller;

import com.kolyanlock.afk_heroes_crud.dto.fraction.FractionDTO;
import com.kolyanlock.afk_heroes_crud.dto.fraction.FractionWithHeroListDTO;
import com.kolyanlock.afk_heroes_crud.service.FractionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class FractionController {
    private final FractionService fractionService;

    @GetMapping("/factions")
    public List<FractionDTO> getAllFactions(Sort sort) {
        return fractionService.getAllFactions(sort);
    }

    @GetMapping("/factions/{title}")
    public Page<FractionWithHeroListDTO> getFraction(@PathVariable String title, @PageableDefault Pageable pageable) {
        return fractionService.getFraction(title, pageable);
    }

    @PostMapping("/factions/new")
    public FractionDTO addNewFraction(@Valid @RequestBody FractionDTO fractionDTO) {
        return fractionService.addNewFraction(fractionDTO);
    }

    @PutMapping("/factions/{oldTitle}")
    public Page<FractionWithHeroListDTO> updateFraction(@PathVariable String oldTitle,
                                                        @Valid @RequestBody FractionDTO fractionDTO,
                                                        Pageable pageable) {
        return fractionService.updateFraction(oldTitle, fractionDTO, pageable);
    }

    @DeleteMapping("/factions/{title}")
    public String deleteFraction(@PathVariable String title) {
        return fractionService.deleteFraction(title);
    }
}
