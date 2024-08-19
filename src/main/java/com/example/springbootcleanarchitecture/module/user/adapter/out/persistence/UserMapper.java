package com.example.springbootcleanarchitecture.module.user.adapter.out.persistence;

import com.example.springbootcleanarchitecture.common.mapper.EntityMapper;
import com.example.springbootcleanarchitecture.module.user.domain.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends EntityMapper<User, UserEntity> {
}
