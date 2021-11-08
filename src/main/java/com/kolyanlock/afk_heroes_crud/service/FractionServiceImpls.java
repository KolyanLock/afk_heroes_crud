package com.kolyanlock.afk_heroes_crud.service;

import com.kolyanlock.afk_heroes_crud.dao.FractionRepository;
import com.kolyanlock.afk_heroes_crud.dto.fraction.FractionDTO;
import com.kolyanlock.afk_heroes_crud.dto.fraction.FractionWithHeroListDTO;
import com.kolyanlock.afk_heroes_crud.entity.Fraction;
import com.kolyanlock.afk_heroes_crud.mappers.FractionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FractionServiceImpls implements FractionService {
    private final FractionRepository fractionRepository;


    @Override
    public List<FractionDTO> getAllFactions(Sort sort) {
        return FractionMapper.INSTANCE.toListFractionDTO(fractionRepository.findAll(sort));
    }

    @Override
    public Page<FractionWithHeroListDTO> getFraction(String title, Pageable pageable) {
        return fractionRepository.findByTitle(title, pageable).map(FractionMapper.INSTANCE::toFractionWithHeroListDTO);
    }

    @Override
    public FractionDTO addNewFraction(FractionDTO fractionDTO) {
        String id = fractionDTO.getTitle();
        if (fractionRepository.findById(id).isPresent()) {
            fractionDTO.setDescription("This fraction already exists!");
            return fractionDTO;
            //"Fraction with title " + id + " already exists!";
        }
        Fraction newFraction = FractionMapper.INSTANCE.toFractionEntity(fractionDTO);
        fractionRepository.save(newFraction);
        return fractionDTO;
    }

    @Override
    public Page<FractionWithHeroListDTO> updateFraction(String oldTitle, FractionDTO fractionDTO, Pageable pageable) {
        String newTitle = fractionDTO.getTitle();
        String newDescription = fractionDTO.getDescription();
        fractionRepository.updateQuery(newTitle, newDescription, oldTitle);
        return getFraction(newTitle, pageable);
    }

    @Override
    public String deleteFraction(String title) {
        fractionRepository.deleteById(title);
        return "Fraction with title " + title + " was deleted";
    }
}
