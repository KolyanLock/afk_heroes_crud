package com.kolyanlock.afk_heroes_crud.controller;

import com.kolyanlock.afk_heroes_crud.dto.hero.HeroForListDTO;
import com.kolyanlock.afk_heroes_crud.dto.hero.HeroMainDTO;
import com.kolyanlock.afk_heroes_crud.service.HeroService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

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
    public HeroMainDTO getEmployee(@PathVariable int id) {
        return heroService.getHeroById(id);
    }

    @PostMapping("/heroes/new")
    public HeroMainDTO addNewHero(@RequestBody HeroMainDTO heroMainDTO) {
        return heroService.addNewHero(heroMainDTO);
    }

    @PutMapping("/heroes/update")
    public HeroMainDTO updateHero(@RequestBody HeroMainDTO heroMainDTO) {
        return heroService.updateHero(heroMainDTO);
    }
    @DeleteMapping("/heroes/{id}")
    public String deleteHero(@PathVariable int id) {
        return heroService.deleteHero(id);
    }
}
