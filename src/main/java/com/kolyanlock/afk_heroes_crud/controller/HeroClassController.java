package com.kolyanlock.afk_heroes_crud.controller;

import com.kolyanlock.afk_heroes_crud.dto.heroclass.HeroClassDTO;
import com.kolyanlock.afk_heroes_crud.dto.heroclass.HeroClassWithHeroListDTO;
import com.kolyanlock.afk_heroes_crud.service.HeroClassService;
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
public class HeroClassController {
    private final HeroClassService heroClassService;

    @GetMapping("/classes")
    public List<HeroClassDTO> getAllClasses(Sort sort) {
        return heroClassService.getAllHeroClasses(sort);
    }

    @GetMapping("/classes/{title}")
    public Page<HeroClassWithHeroListDTO> getFraction(@PathVariable String title, @PageableDefault Pageable pageable) {
        return heroClassService.getHeroClass(title, pageable);
    }

    @PostMapping("/classes/new")
    public HeroClassDTO addNewFraction(@Valid @RequestBody HeroClassDTO heroClassDTO) {
        return heroClassService.addNewHeroClass(heroClassDTO);
    }

    @PutMapping("/classes/{oldTitle}")
    public Page<HeroClassWithHeroListDTO> updateFraction(@PathVariable String oldTitle,
                                                         @Valid @RequestBody HeroClassDTO heroClassDTO,
                                                         Pageable pageable) {
        return heroClassService.updateHeroClass(oldTitle, heroClassDTO, pageable);
    }

    @DeleteMapping("/classes/{title}")
    public String deleteFraction(@PathVariable String title) {
        return heroClassService.deleteHeroClass(title);
    }
}
