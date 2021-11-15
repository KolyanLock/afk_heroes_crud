package com.kolyanlock.afk_heroes_crud.service;

import com.kolyanlock.afk_heroes_crud.dao.FractionRepository;
import com.kolyanlock.afk_heroes_crud.dto.fraction.FractionDTO;
import com.kolyanlock.afk_heroes_crud.entity.Fraction;
import com.kolyanlock.afk_heroes_crud.exception.EntityExistsException;
import com.kolyanlock.afk_heroes_crud.exception.EntityNotFoundException;
import com.kolyanlock.afk_heroes_crud.mappers.FractionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FractionServiceImpls implements FractionService {
    private final FractionRepository fractionRepository;


    @Override
    public Page<FractionDTO> getAllFactions(Pageable pageable) {
        return fractionRepository.findAll(pageable).map(FractionMapper.INSTANCE::toFractionDTO);
    }

    @Override
    public FractionDTO getFraction(String title) {
        Optional<Fraction> optionalFraction = fractionRepository.findById(title);
        if (!optionalFraction.isPresent())
            throw new EntityNotFoundException("Fraction with tittle " + title + " not found!");
        return FractionMapper.INSTANCE.toFractionDTO(optionalFraction.get());
    }

    @Override
    public FractionDTO addNewFraction(FractionDTO fractionDTO) {
        String id = fractionDTO.getTitle();
        if (fractionRepository.findById(id).isPresent()) {
            throw new EntityExistsException("Fraction with title " + id + " already exists!");
        }
        Fraction fractionForSave = FractionMapper.INSTANCE.toFractionEntity(fractionDTO);
        Fraction savedFraction = fractionRepository.save(fractionForSave);
        return FractionMapper.INSTANCE.toFractionDTO(savedFraction);
    }

    @Override
    public FractionDTO updateFraction(String oldTitle, FractionDTO fractionDTO) {
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
        Optional<Fraction> optionalFraction = fractionRepository.findById(newTitle);
        if (!optionalFraction.isPresent()) {
           throw new EntityNotFoundException("Fraction with tittle " + oldTitle + " not found!");
        }
        return FractionMapper.INSTANCE.toFractionDTO(optionalFraction.get());
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
