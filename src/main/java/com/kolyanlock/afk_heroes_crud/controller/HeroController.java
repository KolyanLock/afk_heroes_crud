package com.kolyanlock.afk_heroes_crud.controller;

import com.kolyanlock.afk_heroes_crud.dto.hero.HeroDTO;
import com.kolyanlock.afk_heroes_crud.dto.hero.HeroForListDTO;
import com.kolyanlock.afk_heroes_crud.service.HeroService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class HeroController {
    private final HeroService heroService;

    @GetMapping("/heroes")
    public Page<HeroForListDTO> getAllHeroes(@PageableDefault(sort = {"name"}) Pageable pageable) {
        return heroService.getAllHeroes(pageable);
    }

    @GetMapping("/heroes/{id}")
    public HeroDTO getEmployee(@PathVariable int id) {
        return heroService.getHeroById(id);
    }

    @PostMapping("/heroes/new")
    public HeroDTO addNewHero(@Valid @RequestBody HeroDTO heroDTO) {
        return heroService.addNewHero(heroDTO);
    }

    @PutMapping("/heroes/update")
    public HeroDTO updateHero(@Valid @RequestBody HeroDTO heroDTO) {
        return heroService.updateHero(heroDTO);
    }
    @DeleteMapping("/heroes/{id}")
    public String deleteHero(@PathVariable int id) {
        return heroService.deleteHero(id);
    }
}
