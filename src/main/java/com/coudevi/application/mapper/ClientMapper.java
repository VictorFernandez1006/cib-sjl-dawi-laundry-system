package com.coudevi.application.mapper;

import com.coudevi.domain.model.ClientEntity;
import com.coudevi.domain.model.UserEntity;
import com.coudevi.web.dto.ClientCreateRequestDto;
import com.coudevi.web.dto.ClientResponseDto;

public interface ClientMapper {
    ClientEntity toEntity(ClientCreateRequestDto requestDto, UserEntity user);
    ClientResponseDto toDto(ClientEntity entity);

}
