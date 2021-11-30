package com.kolyanlock.afk_heroes_crud.controller;

import com.kolyanlock.afk_heroes_crud.dto.heroClass.HeroClassDTO;
import com.kolyanlock.afk_heroes_crud.service.HeroClassService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/classes")
@RequiredArgsConstructor
public class HeroClassController {
    private final HeroClassService heroClassService;

    @GetMapping
    public List<HeroClassDTO> getAllHeroClasses(Sort sort) {
        return heroClassService.getAllHeroClasses(sort);
    }

    @GetMapping("/{title}")
    public HeroClassDTO getHeroClass(@PathVariable String title) {
        return heroClassService.getHeroClass(title);
    }

    @PostMapping("/new")
    public HeroClassDTO addNewHeroClass(@Valid @RequestBody HeroClassDTO heroClassDTO) {
        return heroClassService.addNewHeroClass(heroClassDTO);
    }

    @PutMapping("/{oldTitle}")
    public HeroClassDTO updateHeroClass(@PathVariable String oldTitle,
                                        @Valid @RequestBody HeroClassDTO heroClassDTO) {
        return heroClassService.updateHeroClass(oldTitle, heroClassDTO);
    }

    @DeleteMapping("/{title}")
    public String deleteHeroClass(@PathVariable String title) {
        return heroClassService.deleteHeroClass(title);
    }
}
