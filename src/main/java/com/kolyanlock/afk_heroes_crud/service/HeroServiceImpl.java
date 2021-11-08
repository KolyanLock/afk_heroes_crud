package com.kolyanlock.afk_heroes_crud.service;

import com.kolyanlock.afk_heroes_crud.dao.HeroRepository;
import com.kolyanlock.afk_heroes_crud.dto.hero.HeroDTO;
import com.kolyanlock.afk_heroes_crud.dto.hero.HeroForListDTO;
import com.kolyanlock.afk_heroes_crud.entity.Hero;
import com.kolyanlock.afk_heroes_crud.mappers.HeroMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HeroServiceImpl implements HeroService {
    private final HeroRepository heroRepository;


    @Override
    public Page<HeroForListDTO> getAllHeroes(Pageable pageable) {
        return heroRepository.findAll(pageable).map(HeroMapper.INSTANCE::toHeroForListDTO);
    }

    @Override
    public HeroDTO getHeroById(int id) {
        return HeroMapper.INSTANCE.toHeroDTO(heroRepository.getById(id));
    }

    @Override
    public HeroDTO addNewHero(HeroDTO heroDTO) {
        Hero newHero = heroRepository.save(HeroMapper.INSTANCE.toHeroEntity(heroDTO));
        return HeroMapper.INSTANCE.toHeroDTO(newHero);
    }

    @Override
    public HeroDTO updateHero(HeroDTO heroDTO) {
        Hero heroForUpdate = HeroMapper.INSTANCE.toHeroEntity(heroDTO);
        heroRepository.save(heroForUpdate);
        return HeroMapper.INSTANCE.toHeroDTO(heroForUpdate);
    }

    @Override
    public String deleteHero(int id) {
        heroRepository.deleteById(id);
        return "Hero with id = " + id + " was deleted";
    }
}
