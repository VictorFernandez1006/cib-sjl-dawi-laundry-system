package com.coudevi.application.mapper.impl;

import com.coudevi.application.mapper.ServiceTypeMapper;
import com.coudevi.domain.model.ServiceTypeEntity;
import com.coudevi.web.dto.ServiceTypeRequestDto;
import com.coudevi.web.dto.ServiceTypeResponseDto;
import org.springframework.stereotype.Component;

@Component
public class ServiceTypeMapperImpl implements ServiceTypeMapper {
    @Override
    public ServiceTypeEntity toEntity(ServiceTypeRequestDto dto) {
        return ServiceTypeEntity.builder()
                .name(dto.getName().strip())
                .pricePerKg(dto.getPricePerKg())
                .enabled(true)
                .build();
    }

    @Override
    public void updateEntity(ServiceTypeEntity entity, ServiceTypeRequestDto dto) {
        entity.setName(dto.getName().strip());
        entity.setPricePerKg(dto.getPricePerKg());
    }

    @Override
    public ServiceTypeResponseDto toDto(ServiceTypeEntity entity) {
        return ServiceTypeResponseDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .pricePerKg(entity.getPricePerKg())
                .enabled(Boolean.TRUE.equals(entity.getEnabled()))
                .build();
    }
}
