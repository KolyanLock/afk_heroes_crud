package com.kolyanlock.afk_heroes_crud.service;

import com.kolyanlock.afk_heroes_crud.dao.HeroRepository;
import com.kolyanlock.afk_heroes_crud.dto.hero.HeroDTO;
import com.kolyanlock.afk_heroes_crud.dto.hero.HeroForListDTO;
import com.kolyanlock.afk_heroes_crud.entity.Hero;
import com.kolyanlock.afk_heroes_crud.exception.EntityNotFoundException;
import com.kolyanlock.afk_heroes_crud.mappers.HeroMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;


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
        Optional<Hero> optionalHero = heroRepository.findById(id);
        if (!optionalHero.isPresent()) {
            throw new EntityNotFoundException("Hero with tittle " + id + " not found!");
        }
        return HeroMapper.INSTANCE.toHeroDTO(optionalHero.get());
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
        try {
            heroRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException("Hero with id " + id + " not found!");
        }
        return "Hero with id = " + id + " was deleted";
    }

    @Override
    public Page<HeroDTO> findAllByFraction(String title, Pageable pageable) {
        return heroRepository.findAllByFraction(title, pageable).map(HeroMapper.INSTANCE::toHeroDTO);
    }

    @Override
    public Page<HeroDTO> findAllByHeroClass(String heroClass, Pageable pageable) {
        return heroRepository.findAllByHeroClass(heroClass, pageable).map(HeroMapper.INSTANCE::toHeroDTO);
    }

    @Override
    public Page<HeroDTO> findAllByRole(String role, Pageable pageable) {
        return heroRepository.findAllByPrimaryRole(role, pageable).map(HeroMapper.INSTANCE::toHeroDTO);
    }

    @Override
    public Page<HeroDTO> findAllByType(String type, Pageable pageable) {
        return heroRepository.findAllByType(type, pageable).map(HeroMapper.INSTANCE::toHeroDTO);
    }
}
