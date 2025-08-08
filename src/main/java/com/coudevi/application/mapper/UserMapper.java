package com.coudevi.application.mapper;

import com.coudevi.domain.model.RoleEntity;
import com.coudevi.domain.model.UserEntity;
import com.coudevi.web.dto.UserRequestDto;
import com.coudevi.web.dto.UserResponseDto;
import com.coudevi.web.dto.UserStatusUpdateDto;

public interface UserMapper {
    UserEntity toEntity(UserRequestDto dto, RoleEntity role);
    UserResponseDto toDto(UserEntity entity);
    void updateStatusFromDto(UserEntity user, UserStatusUpdateDto dto);
}
