package com.firstdecision.appfirstdecisionbackend.util;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public final class Converter {

    private Converter() {
        throw new IllegalStateException("Utility class");
    }

    private static final ModelMapper modelMapper = new ModelMapper();

    static {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    public static <D, E> D entityToDto(E entity, Class<D> dtoClass) {
        return modelMapper.map(entity, dtoClass);
    }

    public static <E, D> E dtoToEntity(D dto, Class<E> entityClass) {
        return modelMapper.map(dto, entityClass);
    }

    public static <D, E> List<E> dtoListToEntityList(List<D> dtos, Class<E> entityClass) {
        return dtos.stream()
                .map(dto -> modelMapper.map(dto, entityClass))
                .collect(Collectors.toList());
    }

    public static <E, D> List<D> entityListToDtoList(List<E> entities, Class<D> dtoClass) {
        return entities.stream()
                .map(entity -> modelMapper.map(entity, dtoClass))
                .collect(Collectors.toList());
    }

    public static <D, E> Page<E> dtoPageToEntityPage(Page<D> dtos, Class<E> entityClass) {
        return dtos.map(dto -> modelMapper.map(dto, entityClass));
    }

    public static <E, D> Page<D> entityPageToDtoPage(Page<E> entities, Class<D> dtoClass) {
        return entities.map(entity -> modelMapper.map(entity, dtoClass));
    }

}
