package com.coudevi.application.mapper.impl;

import com.coudevi.application.mapper.UserMapper;
import com.coudevi.domain.model.RoleEntity;
import com.coudevi.domain.model.UserEntity;
import com.coudevi.web.dto.UserRequestDto;
import com.coudevi.web.dto.UserResponseDto;
import com.coudevi.web.dto.UserStatusUpdateDto;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UserMapperImpl implements UserMapper {
    @Override
    public UserEntity toEntity(UserRequestDto dto, RoleEntity role) {
        return UserEntity.builder()
                .username(dto.getUsername())
                .password(dto.getPassword())
                .fullName(dto.getFullName())
                .email(dto.getEmail())
                .roles(Set.of(role))
                .enabled(true)
                .build();
    }

    @Override
    public UserResponseDto toDto(UserEntity entity) {
        return UserResponseDto.builder()
                .id(entity.getId())
                .username(entity.getUsername())
                .fullName(entity.getFullName())
                .email(entity.getEmail())
                .roles(entity.getRoles().stream()
                        .map(RoleEntity::getName)
                        .collect(Collectors.toSet()))
                .enabled(entity.getEnabled())
                .build();
    }

    @Override
    public void updateStatusFromDto(UserEntity user, UserStatusUpdateDto dto) {
        user.setEnabled(dto.getEnabled());
    }
}
