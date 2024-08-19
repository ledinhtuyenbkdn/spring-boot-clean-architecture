package com.example.springbootcleanarchitecture.common.mapper;

import java.util.List;

public interface EntityMapper<D, E> {

    E toEntity(D domainModel);

    D toDomainModel(E entity);

    List<E> toEntity(List<D> domainModels);

    List<D> toDomainModel(List<E> entities);
}
