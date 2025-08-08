package com.coudevi.application.mapper.impl;

import com.coudevi.application.mapper.ClientMapper;
import com.coudevi.domain.model.ClientEntity;
import com.coudevi.domain.model.UserEntity;
import com.coudevi.web.dto.ClientCreateRequestDto;
import com.coudevi.web.dto.ClientResponseDto;
import org.springframework.stereotype.Component;

@Component
public class ClientMapperImpl implements ClientMapper {
    @Override
    public ClientEntity toEntity(ClientCreateRequestDto requestDto, UserEntity user) {
        return ClientEntity.builder()
                .documentId(requestDto.getDocumentId())
                .fullName(requestDto.getFullName())
                .phone(requestDto.getPhone())
                .email(requestDto.getEmail())
                .address(requestDto.getAddress())
                .user(user)
                .build();
    }

    @Override
    public ClientResponseDto toDto(ClientEntity entity) {
        return ClientResponseDto.builder()
                .id(entity.getId())
                .documentId(entity.getDocumentId())
                .fullName(entity.getFullName())
                .phone(entity.getPhone())
                .email(entity.getEmail())
                .address(entity.getAddress())
                .userId(entity.getUser().getId())
                .username(entity.getUser().getUsername())
                .enabled(Boolean.TRUE.equals(entity.getUser().getEnabled()))
                .build();
    }
}
