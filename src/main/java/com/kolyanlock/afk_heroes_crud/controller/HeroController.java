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
    public Page<HeroForListDTO> getAllHeroes(@PageableDefault(sort = {"fraction", "name"}, page = 1) Pageable pageable) {
        return heroService.getAllHeroes(pageable);
    }

    @GetMapping("/heroes/{id}")
    public HeroDTO getHero(@PathVariable int id) {
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

    @GetMapping("/factions/{title}/heroes")
    public Page<HeroDTO> getAllHeroesByFraction(@PathVariable String title,
                                                @PageableDefault(size = 2) Pageable pageable) {
        return heroService.findAllByFraction(title, pageable);
    }

    @GetMapping("/classes/{heroClass}/heroes")
    public Page<HeroDTO> getAllHeroesByHeroClass(@PathVariable String heroClass,
                                                 @PageableDefault(size = 2) Pageable pageable) {
        return heroService.findAllByHeroClass(heroClass, pageable);
    }

    @GetMapping("/roles/{role}/heroes")
    public Page<HeroDTO> getAllHeroesByRole(@PathVariable String role,
                                            @PageableDefault(size = 2) Pageable pageable) {
        return heroService.findAllByRole(role, pageable);
    }

    @GetMapping("/types/{type}/heroes")
    public Page<HeroDTO> getAllHeroesByType(@PathVariable String type,
                                            @PageableDefault(size = 2) Pageable pageable) {
        return heroService.findAllByType(type, pageable);
    }
}
