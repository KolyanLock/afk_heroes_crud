package com.kolyanlock.afk_heroes_crud.service;

import com.kolyanlock.afk_heroes_crud.dao.HeroRepository;
import com.kolyanlock.afk_heroes_crud.dto.hero.HeroDTO;
import com.kolyanlock.afk_heroes_crud.dto.hero.HeroForListDTO;
import com.kolyanlock.afk_heroes_crud.entity.Hero;
import com.kolyanlock.afk_heroes_crud.exception.HeroNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.kolyanlock.afk_heroes_crud.mappers.HeroMapper.HERO_MAPPER;


@Service
@RequiredArgsConstructor
public class HeroServiceImpl implements HeroService {
    private final HeroRepository heroRepository;


    @Override
    public Page<HeroForListDTO> getAllHeroes(Pageable pageable) {
        return heroRepository.findAll(pageable).map(HERO_MAPPER::toHeroForListDTO);
    }

    @Override
    public HeroDTO getHeroById(int id) {
        Optional<Hero> optionalHero = heroRepository.findById(id);
        if (!optionalHero.isPresent()) {
            throw new HeroNotFoundException(id);
        }
        return HERO_MAPPER.toHeroDTO(optionalHero.get());
    }

    @Override
    public HeroDTO addNewHero(HeroDTO heroDTO) {
        Hero newHero = HERO_MAPPER.toHeroEntity(heroDTO);
        return HERO_MAPPER.toHeroDTO(heroRepository.save(newHero));
    }

    @Override
    public HeroDTO updateHero(HeroDTO heroDTO) {
        Hero heroForUpdate = HERO_MAPPER.toHeroEntity(heroDTO);
        return HERO_MAPPER.toHeroDTO(heroRepository.save(heroForUpdate));
    }

    @Override
    public String deleteHero(int id) {
        try {
            heroRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new HeroNotFoundException(id);
        }
        return "Hero with id = " + id + " was deleted";
    }

    @Override
    public Page<HeroDTO> findAllByFraction(String title, Pageable pageable) {
        return heroRepository.findAllByFraction(title, pageable).map(HERO_MAPPER::toHeroDTO);
    }

    @Override
    public Page<HeroDTO> findAllByHeroClass(String heroClass, Pageable pageable) {
        return heroRepository.findAllByHeroClass(heroClass, pageable).map(HERO_MAPPER::toHeroDTO);
    }

    @Override
    public Page<HeroDTO> findAllByRole(String role, Pageable pageable) {
        return heroRepository.findAllByPrimaryRole(role, pageable).map(HERO_MAPPER::toHeroDTO);
    }

    @Override
    public Page<HeroDTO> findAllByType(String type, Pageable pageable) {
        return heroRepository.findAllByType(type, pageable).map(HERO_MAPPER::toHeroDTO);
    }
}
