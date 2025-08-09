package com.coudevi.application.mapper;

import com.coudevi.domain.model.ServiceTypeEntity;
import com.coudevi.web.dto.ServiceTypeRequestDto;
import com.coudevi.web.dto.ServiceTypeResponseDto;

public interface ServiceTypeMapper {
    ServiceTypeEntity toEntity(ServiceTypeRequestDto dto);
    void updateEntity(ServiceTypeEntity entity, ServiceTypeRequestDto dto);
    ServiceTypeResponseDto toDto(ServiceTypeEntity entity);

}
