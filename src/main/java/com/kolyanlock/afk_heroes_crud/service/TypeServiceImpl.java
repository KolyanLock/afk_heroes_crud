package com.kolyanlock.afk_heroes_crud.service;

import com.kolyanlock.afk_heroes_crud.dao.TypeRepository;
import com.kolyanlock.afk_heroes_crud.dto.type.TypeDTO;
import com.kolyanlock.afk_heroes_crud.entity.Type;
import com.kolyanlock.afk_heroes_crud.exception.EntityExistsException;
import com.kolyanlock.afk_heroes_crud.exception.EntityNotFoundException;
import com.kolyanlock.afk_heroes_crud.mappers.TypeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TypeServiceImpl implements TypeService {
    private final TypeRepository typeRepository;

    @Override
    public List<TypeDTO> getAllTypes(Sort sort) {
        return TypeMapper.INSTANCE.toListTypeDTO(typeRepository.findAll(sort));
    }

    @Override
    public TypeDTO getType(String type) {
        Optional<Type> typeDTOOptional = typeRepository.findById(type);
        if (!typeDTOOptional.isPresent())
            throw new EntityNotFoundException("Type with tittle " + type + " not found!");
        return TypeMapper.INSTANCE.toTypeDTO(typeDTOOptional.get());
    }

    @Override
    public TypeDTO addNewType(TypeDTO typeDTO) {
        String id = typeDTO.getType();
        if (typeRepository.findById(id).isPresent()){
            throw new EntityExistsException("Type with type " + id + " already exists!");
        }
        Type newType = TypeMapper.INSTANCE.toTypeEntity(typeDTO);
        typeRepository.save(newType);
        return typeDTO;
    }

    @Override
    public TypeDTO updateType(String oldType, TypeDTO typeDTO) {
        if (!typeRepository.findById(oldType).isPresent()) {
            throw new EntityNotFoundException("Type with tittle " + oldType + " not found!");
        }
        String newType = typeDTO.getType();
        String newDescription = typeDTO.getDescription();
        try {
            typeRepository.updateQuery(newType, newDescription, oldType);
        } catch (DataIntegrityViolationException e) {
            throw new EntityExistsException("Type with type " + newType + " already exists!");
        }
        Optional<Type> optionalType = typeRepository.findById(newType);
        if (!optionalType.isPresent()) {
            throw new EntityNotFoundException("Type with tittle " + oldType + " not found!");
        }
        return TypeMapper.INSTANCE.toTypeDTO(optionalType.get());
    }

    @Override
    public String deleteType(String type) {
        try {
            typeRepository.deleteById(type);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException("Type with tittle " + type + " not found!");
        }
        return "Type with type " + type + " was deleted";
    }
}
