package com.coudevi.application.service;

import com.coudevi.web.dto.ServiceTypeRequestDto;
import com.coudevi.web.dto.ServiceTypeResponseDto;
import com.coudevi.web.dto.ServiceTypeStatusUpdateDto;

import java.util.List;

public interface ServiceTypeService {
    ServiceTypeResponseDto create(ServiceTypeRequestDto dto);
    ServiceTypeResponseDto update(Long id, ServiceTypeRequestDto dto);
    ServiceTypeResponseDto updateStatus(Long id, ServiceTypeStatusUpdateDto dto);
    ServiceTypeResponseDto getById(Long id);
    List<ServiceTypeResponseDto> listAll();
    List<ServiceTypeResponseDto> listEnabled();
}
