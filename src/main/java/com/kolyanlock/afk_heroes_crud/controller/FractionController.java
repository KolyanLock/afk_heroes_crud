package com.kolyanlock.afk_heroes_crud.controller;

import com.kolyanlock.afk_heroes_crud.dto.fraction.FractionDTO;
import com.kolyanlock.afk_heroes_crud.service.FractionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class FractionController {
    private final FractionService fractionService;

    @GetMapping("/factions")
    public Page<FractionDTO> getAllFactions(@PageableDefault Pageable pageable) {
        return fractionService.getAllFactions(pageable);
    }

    @GetMapping("/factions/{title}")
    public FractionDTO getFraction(@PathVariable String title) {
        return fractionService.getFraction(title);
    }

    @PostMapping("/factions/new")
    public FractionDTO addNewFraction(@Valid @RequestBody FractionDTO fractionDTO) {
        return fractionService.addNewFraction(fractionDTO);
    }

    @PutMapping("/factions/{oldTitle}")
    public FractionDTO updateFraction(@PathVariable String oldTitle,
                                      @Valid @RequestBody FractionDTO fractionDTO) {
        return fractionService.updateFraction(oldTitle, fractionDTO);
    }

    @DeleteMapping("/factions/{title}")
    public String deleteFraction(@PathVariable String title) {
        return fractionService.deleteFraction(title);
    }
}
