package com.kolyanlock.afk_heroes_crud.service;

import com.kolyanlock.afk_heroes_crud.dao.Fraction.FractionRepository;
import com.kolyanlock.afk_heroes_crud.dto.fraction.FractionDTO;
import com.kolyanlock.afk_heroes_crud.entity.Fraction;
import com.kolyanlock.afk_heroes_crud.exception.FractionExistsException;
import com.kolyanlock.afk_heroes_crud.exception.FractionNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.kolyanlock.afk_heroes_crud.mappers.FractionMapper.FRACTION_MAPPER;

@Service
@RequiredArgsConstructor
public class FractionServiceImpls implements FractionService {
    private final FractionRepository fractionRepository;


    @Override
    public Page<FractionDTO> getAllFactions(Pageable pageable) {
        return fractionRepository.findAll(pageable).map(FRACTION_MAPPER::toFractionDTO);
    }

    @Override
    public FractionDTO getFraction(String title) {
        Optional<Fraction> optionalFraction = fractionRepository.findById(title);
        if (!optionalFraction.isPresent())
            throw new FractionNotFoundException(title);
        return FRACTION_MAPPER.toFractionDTO(optionalFraction.get());
    }

    @Override
    public FractionDTO addNewFraction(FractionDTO fractionDTO) {
        String id = fractionDTO.getTitle();
        if (fractionRepository.findById(id).isPresent()) {
            throw new FractionExistsException(id);
        }
        Fraction fractionForSave = FRACTION_MAPPER.toFractionEntity(fractionDTO);
        Fraction savedFraction = fractionRepository.save(fractionForSave);
        return FRACTION_MAPPER.toFractionDTO(savedFraction);
    }

    @Override
    public FractionDTO updateFraction(String oldTitle, FractionDTO fractionDTO) {
        if (!fractionRepository.findById(oldTitle).isPresent()) {
            throw new FractionNotFoundException(oldTitle);
        }
        String newTitle = fractionDTO.getTitle();
        String newDescription = fractionDTO.getDescription();
        if (fractionRepository.findById(newTitle).isPresent()) {
            throw new FractionExistsException(newTitle);
        }
        fractionRepository.update(newTitle, newDescription, oldTitle);
        return fractionDTO;
    }

    @Override
    public String deleteFraction(String title) {
        try {
            fractionRepository.deleteById(title);
        } catch (EmptyResultDataAccessException e) {
            throw new FractionNotFoundException(title);
        }
        return "Fraction with title " + title + " was deleted";
    }
}
