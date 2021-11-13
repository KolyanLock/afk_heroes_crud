package com.kolyanlock.afk_heroes_crud.service;

import com.kolyanlock.afk_heroes_crud.dao.FractionRepository;
import com.kolyanlock.afk_heroes_crud.dto.fraction.FractionDTO;
import com.kolyanlock.afk_heroes_crud.dto.fraction.FractionWithHeroListDTO;
import com.kolyanlock.afk_heroes_crud.entity.Fraction;
import com.kolyanlock.afk_heroes_crud.exception.EntityExistsException;
import com.kolyanlock.afk_heroes_crud.exception.EntityNotFoundException;
import com.kolyanlock.afk_heroes_crud.mappers.FractionMapper;
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
public class FractionServiceImpls implements FractionService {
    private final FractionRepository fractionRepository;


    @Override
    public List<FractionDTO> getAllFactions(Sort sort) {
        return FractionMapper.INSTANCE.toListFractionDTO(fractionRepository.findAll(sort));
    }

    @Override
    public Page<FractionWithHeroListDTO> getFraction(String title, Pageable pageable) {
        Page<Fraction> fractionPage = fractionRepository.getByTitle(title, pageable);
        if (fractionPage.isEmpty())
            throw new EntityNotFoundException("Fraction with tittle " + title + " not found!");
        return fractionRepository.getByTitle(title, pageable).map(FractionMapper.INSTANCE::toFractionWithHeroListDTO);
    }

    @Override
    public FractionDTO addNewFraction(FractionDTO fractionDTO) {
        String id = fractionDTO.getTitle();
        if (fractionRepository.findById(id).isPresent()) {
            throw new EntityExistsException("Fraction with title " + id + " already exists!");
        }
        Fraction newFraction = FractionMapper.INSTANCE.toFractionEntity(fractionDTO);
        fractionRepository.save(newFraction);
        return fractionDTO;
    }

    @Override
    public Page<FractionWithHeroListDTO> updateFraction(String oldTitle, FractionDTO fractionDTO, Pageable pageable) {
        if (!fractionRepository.findById(oldTitle).isPresent()) {
            throw new EntityNotFoundException("Fraction with tittle " + oldTitle + " not found!");
        }
        String newTitle = fractionDTO.getTitle();
        String newDescription = fractionDTO.getDescription();
        try {
            fractionRepository.updateQuery(newTitle, newDescription, oldTitle);
        } catch (DataIntegrityViolationException e) {
            throw new EntityExistsException("Class with title " + newTitle + " already exists!");
        }
        Page<FractionWithHeroListDTO> fractionWithHeroListDTOPage = getFraction(newTitle, pageable);
        if (fractionWithHeroListDTOPage.isEmpty()) {
           throw new EntityNotFoundException("Fraction with tittle " + oldTitle + " not found!");
        }
        return getFraction(newTitle, pageable);
    }

    @Override
    public String deleteFraction(String title) {
        try {
            fractionRepository.deleteById(title);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException("Fraction with tittle " + title + " not found!");
        }
        return "Fraction with title " + title + " was deleted";
    }
}
