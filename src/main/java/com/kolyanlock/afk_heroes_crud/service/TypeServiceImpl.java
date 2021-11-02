package com.kolyanlock.afk_heroes_crud.service;

import com.kolyanlock.afk_heroes_crud.dao.TypeRepository;
import com.kolyanlock.afk_heroes_crud.dto.type.TypeDTO;
import com.kolyanlock.afk_heroes_crud.dto.type.TypeWithHeroListDTO;
import com.kolyanlock.afk_heroes_crud.entity.Type;
import com.kolyanlock.afk_heroes_crud.mappers.TypeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TypeServiceImpl implements TypeService{
    private final TypeRepository typeRepository;

    @Override
    public List<TypeDTO> getAllTypes(Sort sort) {
        return TypeMapper.INSTANCE.toListTypeDTO(typeRepository.findAll(sort));
    }

    @Override
    public Page<TypeWithHeroListDTO> getType(String type, Pageable pageable) {
        return typeRepository.findByType(type, pageable).map(TypeMapper.INSTANCE::toTypeWithHeroListDTO);
    }

    @Override
    public TypeDTO addNewType(TypeDTO typeDTO) {
        String id = typeDTO.getType();
        if (typeRepository.findById(id).isPresent()){
            typeDTO.setDescription("This Type already exists!");
            //"Role with type " + id + " already exists!";
        }
        Type newType = TypeMapper.INSTANCE.toTypeEntity(typeDTO);
        typeRepository.save(newType);
        return typeDTO;
    }

    @Override
    public Page<TypeWithHeroListDTO> updateType(String oldType, TypeDTO typeDTO, Pageable pageable) {
        String newType = typeDTO.getType();
        String newDescription = typeDTO.getDescription();
        try {
            typeRepository.updateQuery(newType, newDescription, oldType);
        } catch (Exception e) {
            System.out.println("Type update!");
        }
        return getType(newType, pageable);
    }

    @Override
    public String deleteType(String type) {
        typeRepository.deleteById(type);
        return "Type with type " + type + " was deleted";
    }
}
