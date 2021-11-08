package com.kolyanlock.afk_heroes_crud.service;

import com.kolyanlock.afk_heroes_crud.dao.HeroClassRepository;
import com.kolyanlock.afk_heroes_crud.dto.heroclass.HeroClassDTO;
import com.kolyanlock.afk_heroes_crud.dto.heroclass.HeroClassWithHeroListDTO;
import com.kolyanlock.afk_heroes_crud.entity.HeroClass;
import com.kolyanlock.afk_heroes_crud.mappers.HeroClassMapper;
import lombok.RequiredArgsConstructor;
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
        return heroClassRepository.findByTitle(title, pageable).map(HeroClassMapper.INSTANCE::toHeroClassWithHeroListDTO);
    }

    @Override
    public HeroClassDTO addNewHeroClass(HeroClassDTO heroClassDTO) {
        String id = heroClassDTO.getTitle();
        if (heroClassRepository.findById(id).isPresent()){
            heroClassDTO.setDescription("This Hero Class already exists!");
            return  heroClassDTO;
             //"Hero Class with title " + id + " already exists!";
        }
        HeroClass newHeroClass = HeroClassMapper.INSTANCE.toHeroClassEntity(heroClassDTO);
        heroClassRepository.save(newHeroClass);
        return heroClassDTO;
    }

    @Override
    public Page<HeroClassWithHeroListDTO> updateHeroClass(String oldTitle, HeroClassDTO heroClassDTO, Pageable pageable) {
        String newTitle = heroClassDTO.getTitle();
        String newDescription = heroClassDTO.getDescription();
        heroClassRepository.updateQuery(newTitle, newDescription, oldTitle);
        return getHeroClass(newTitle, pageable);
    }

    @Override
    public String deleteHeroClass(String title) {
        heroClassRepository.deleteById(title);
        return "Class with title " + title + " was deleted";
    }
}
