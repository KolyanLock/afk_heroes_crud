package com.kolyanlock.afk_heroes_crud.service;

import com.kolyanlock.afk_heroes_crud.dao.HeroRepository;
import com.kolyanlock.afk_heroes_crud.dto.hero.HeroForListDTO;
import com.kolyanlock.afk_heroes_crud.dto.hero.HeroMainDTO;
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
    public HeroMainDTO getHeroById(int id) {
        return HeroMapper.INSTANCE.toHeroMainDTO(heroRepository.getById(id));
    }

    @Override
    public HeroMainDTO addNewHero(HeroMainDTO heroMainDTO) {
        Hero newHero = heroRepository.save(HeroMapper.INSTANCE.toHeroEntity(heroMainDTO));
        return HeroMapper.INSTANCE.toHeroMainDTO(newHero);
    }

    @Override
    public HeroMainDTO updateHero(HeroMainDTO heroMainDTO) {
        Hero heroForUpdate = HeroMapper.INSTANCE.toHeroEntity(heroMainDTO);
        heroRepository.save(heroForUpdate);
        return HeroMapper.INSTANCE.toHeroMainDTO(heroForUpdate);
    }

    @Override
    public String deleteHero(int id) {
        heroRepository.deleteById(id);
        return "Hero with id = " + id + " was deleted";
    }
}
