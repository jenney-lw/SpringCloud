package com.lw.acl.base;

import java.util.List;

public interface BaseMapper<D, E> {

    /**
     * DTO转Entity
     * @param dto
     * @return
     */
    E toEntity(D dto);

    /**
     * Entity转DTO
     */
    D toDTO(E entity);

    /**
     * DTO集合转Entity集合
     */
    List<E> toEntity(List<D> dtoList);

    /**
     * Entity集合转DTO集合
     */
    List <D> toDtoList(List<E> entityList);

}
