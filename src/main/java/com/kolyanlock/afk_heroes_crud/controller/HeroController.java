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
@RequestMapping("/api/heroes")
@RequiredArgsConstructor
public class HeroController {
    private final HeroService heroService;

    @GetMapping
    public Page<HeroForListDTO> getAllHeroes(@PageableDefault(sort = {"fraction", "name"}) Pageable pageable) {
        return heroService.getAllHeroes(pageable);
    }

    @GetMapping("/{id}")
    public HeroDTO getHero(@PathVariable int id) {
        return heroService.getHeroById(id);
    }

    @PostMapping("/new")
    public HeroDTO addNewHero(@Valid @RequestBody HeroDTO heroDTO) {
        return heroService.addNewHero(heroDTO);
    }

    @PutMapping("/update")
    public HeroDTO updateHero(@Valid @RequestBody HeroDTO heroDTO) {
        return heroService.updateHero(heroDTO);
    }

    @DeleteMapping("/{id}")
    public String deleteHero(@PathVariable int id) {
        return heroService.deleteHero(id);
    }

    @GetMapping("/factions/{title}")
    public Page<HeroDTO> getAllHeroesByFraction(@PathVariable String title,
                                                @PageableDefault(size = 2) Pageable pageable) {
        return heroService.findAllByFraction(title, pageable);
    }

    @GetMapping("/classes/{heroClass}")
    public Page<HeroDTO> getAllHeroesByHeroClass(@PathVariable String heroClass,
                                                 @PageableDefault(size = 2) Pageable pageable) {
        return heroService.findAllByHeroClass(heroClass, pageable);
    }

    @GetMapping("/{role}")
    public Page<HeroDTO> getAllHeroesByRole(@PathVariable String role,
                                            @PageableDefault(size = 2) Pageable pageable) {
        return heroService.findAllByRole(role, pageable);
    }

    @GetMapping("/{type}")
    public Page<HeroDTO> getAllHeroesByType(@PathVariable String type,
                                            @PageableDefault(size = 2) Pageable pageable) {
        return heroService.findAllByType(type, pageable);
    }
}
