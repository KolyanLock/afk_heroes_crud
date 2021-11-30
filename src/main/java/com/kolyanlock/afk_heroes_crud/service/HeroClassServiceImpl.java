package com.kolyanlock.afk_heroes_crud.service;

import com.kolyanlock.afk_heroes_crud.dao.HeroClassRepository;
import com.kolyanlock.afk_heroes_crud.dto.heroClass.HeroClassDTO;
import com.kolyanlock.afk_heroes_crud.entity.HeroClass;
import com.kolyanlock.afk_heroes_crud.exception.HeroClassExistsException;
import com.kolyanlock.afk_heroes_crud.exception.HeroClassNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.kolyanlock.afk_heroes_crud.mappers.HeroClassMapper.HERO_CLASS_MAPPER;

@Service
@RequiredArgsConstructor
public class HeroClassServiceImpl implements HeroClassService {
    private final HeroClassRepository heroClassRepository;


    @Override
    public List<HeroClassDTO> getAllHeroClasses(Sort sort) {
        return HERO_CLASS_MAPPER.toListHeroClassDTO(heroClassRepository.findAll(sort));
    }

    @Override
    public HeroClassDTO getHeroClass(String title) {
        Optional<HeroClass> heroClassDTOOptional = heroClassRepository.findById(title);
        if (!heroClassDTOOptional.isPresent())
            throw new HeroClassNotFoundException(title);
        return HERO_CLASS_MAPPER.toHeroClassDTO(heroClassDTOOptional.get());
    }

    @Override
    public HeroClassDTO addNewHeroClass(HeroClassDTO heroClassDTO) {
        String id = heroClassDTO.getTitle();
        if (heroClassRepository.findById(id).isPresent()){
            throw new HeroClassExistsException(id);
        }
        HeroClass newHeroClass = HERO_CLASS_MAPPER.toHeroClassEntity(heroClassDTO);
        heroClassRepository.save(newHeroClass);
        return heroClassDTO;
    }

    @Override
    public HeroClassDTO updateHeroClass(String oldTitle, HeroClassDTO heroClassDTO) {
        if (!heroClassRepository.findById(oldTitle).isPresent()) {
            throw new HeroClassNotFoundException(oldTitle);
        }
        String newTitle = heroClassDTO.getTitle();
        String newDescription = heroClassDTO.getDescription();
        try {
            heroClassRepository.updateQuery(newTitle, newDescription, oldTitle);
        } catch (DataIntegrityViolationException e) {
            throw new HeroClassExistsException(newTitle);
        }
        Optional<HeroClass> optionalHeroClass = heroClassRepository.findById(newTitle);
        if (!optionalHeroClass.isPresent()) {
            throw new HeroClassNotFoundException(oldTitle);
        }
        return HERO_CLASS_MAPPER.toHeroClassDTO(optionalHeroClass.get());
    }

    @Override
    public String deleteHeroClass(String title) {
        try {
            heroClassRepository.deleteById(title);
        } catch (EmptyResultDataAccessException e) {
            throw new HeroClassNotFoundException(title);
        }
        return "Class with title " + title + " was deleted";
    }
}
