package com.example.springbootcleanarchitecture.module.org.adapter.out.persistence;

import com.example.springbootcleanarchitecture.common.mapper.EntityMapper;
import com.example.springbootcleanarchitecture.entity.OrgEntity;
import com.example.springbootcleanarchitecture.module.org.domain.model.Org;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrgMapper extends EntityMapper<Org, OrgEntity> {
}
