package com.kolyanlock.afk_heroes_crud.service;

import com.kolyanlock.afk_heroes_crud.dao.HeroClassRepository;
import com.kolyanlock.afk_heroes_crud.dto.heroclass.HeroClassDTO;
import com.kolyanlock.afk_heroes_crud.dto.heroclass.HeroClassWithHeroListDTO;
import com.kolyanlock.afk_heroes_crud.entity.HeroClass;
import com.kolyanlock.afk_heroes_crud.exception.EntityExistsException;
import com.kolyanlock.afk_heroes_crud.exception.EntityNotFoundException;
import com.kolyanlock.afk_heroes_crud.mappers.HeroClassMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HeroClassServiceImpl implements HeroClassService {
    private final HeroClassRepository heroClassRepository;


    @Override
    public List<HeroClassDTO> getAllHeroClasses(Sort sort) {
        return HeroClassMapper.INSTANCE.toListHeroClassDTO(heroClassRepository.findAll(sort));
    }

    @Override
    public Page<HeroClassWithHeroListDTO> getHeroClass(String title, Pageable pageable) {
        Page<HeroClass> heroClassPage = heroClassRepository.findByTitle(title, pageable);
        if (heroClassPage.isEmpty())
            throw new EntityNotFoundException("Class with tittle " + title + " not found!");
        return heroClassPage.map(HeroClassMapper.INSTANCE::toHeroClassWithHeroListDTO);
    }

    @Override
    public HeroClassDTO addNewHeroClass(HeroClassDTO heroClassDTO) {
        String id = heroClassDTO.getTitle();
        if (heroClassRepository.findById(id).isPresent()){
            throw new EntityExistsException("Class with title " + id + " already exists!");
        }
        HeroClass newHeroClass = HeroClassMapper.INSTANCE.toHeroClassEntity(heroClassDTO);
        heroClassRepository.save(newHeroClass);
        return heroClassDTO;
    }

    @Override
    public Page<HeroClassWithHeroListDTO> updateHeroClass(String oldTitle, HeroClassDTO heroClassDTO, Pageable pageable) {
        if (!heroClassRepository.findById(oldTitle).isPresent()) {
            throw new EntityNotFoundException("Class with tittle " + oldTitle + " not found!");
        }
        String newTitle = heroClassDTO.getTitle();
        String newDescription = heroClassDTO.getDescription();
        try {
            heroClassRepository.updateQuery(newTitle, newDescription, oldTitle);
        } catch (DataIntegrityViolationException e) {
            throw new EntityExistsException("Fraction with title " + newTitle + " already exists!");
        }
        return getHeroClass(newTitle, pageable);
    }

    @Override
    public String deleteHeroClass(String title) {
        try {
            heroClassRepository.deleteById(title);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException("Class with tittle " + title + " not found!");
        }
        return "Class with title " + title + " was deleted";
    }
}
