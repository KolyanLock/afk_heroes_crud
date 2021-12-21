package com.kolyanlock.afk_heroes_crud.service;

import com.kolyanlock.afk_heroes_crud.dao.TypeRepository;
import com.kolyanlock.afk_heroes_crud.dto.type.TypeDTO;
import com.kolyanlock.afk_heroes_crud.entity.Type;
import com.kolyanlock.afk_heroes_crud.exception.TypeExistsException;
import com.kolyanlock.afk_heroes_crud.exception.TypeNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.kolyanlock.afk_heroes_crud.mappers.TypeMapper.TYPE_MAPPER;

@Service
@RequiredArgsConstructor
public class TypeServiceImpl implements TypeService {
    private final TypeRepository typeRepository;

    @Override
    public List<TypeDTO> getAllTypes(Sort sort) {
        return TYPE_MAPPER.toListTypeDTO(typeRepository.findAll(sort));
    }

    @Override
    public TypeDTO getType(String type) {
        Optional<Type> typeDTOOptional = typeRepository.findById(type);
        if (!typeDTOOptional.isPresent())
            throw new TypeNotFoundException(type);
        return TYPE_MAPPER.toTypeDTO(typeDTOOptional.get());
    }

    @Override
    public TypeDTO addNewType(TypeDTO typeDTO) {
        String id = typeDTO.getType();
        if (typeRepository.findById(id).isPresent()){
            throw new TypeExistsException(id);
        }
        Type newType = TYPE_MAPPER.toTypeEntity(typeDTO);
        return TYPE_MAPPER.toTypeDTO(typeRepository.save(newType));
    }

    @Override
    public TypeDTO updateType(String oldType, TypeDTO typeDTO) {
        if (!typeRepository.findById(oldType).isPresent()) {
            throw new TypeNotFoundException(oldType);
        }
        String newType = typeDTO.getType();
        String newDescription = typeDTO.getDescription();
        try {
            typeRepository.updateQuery(newType, newDescription, oldType);
        } catch (DataIntegrityViolationException e) {
            throw new TypeExistsException(newType);
        }
        return typeDTO;
    }

    @Override
    public String deleteType(String type) {
        try {
            typeRepository.deleteById(type);
        } catch (EmptyResultDataAccessException e) {
            throw new TypeNotFoundException(type);
        }
        return "Type with type " + type + " was deleted";
    }
}
