package com.coudevi.application.service.impl;

import com.coudevi.application.mapper.ServiceTypeMapper;
import com.coudevi.application.service.ServiceTypeService;
import com.coudevi.domain.model.ServiceTypeEntity;
import com.coudevi.domain.repository.ServiceTypeRepository;
import com.coudevi.web.dto.ServiceTypeRequestDto;
import com.coudevi.web.dto.ServiceTypeResponseDto;
import com.coudevi.web.dto.ServiceTypeStatusUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@RequiredArgsConstructor
public class ServiceTypeServiceImpl implements ServiceTypeService {
    private final ServiceTypeRepository repo;
    private final ServiceTypeMapper mapper;
    @Override
    @Transactional
    public ServiceTypeResponseDto create(ServiceTypeRequestDto dto) {
        ServiceTypeEntity saved = repo.save(mapper.toEntity(dto));
        return mapper.toDto(saved);
    }

    @Override
    @Transactional
    public ServiceTypeResponseDto update(Long id, ServiceTypeRequestDto dto) {
        ServiceTypeEntity entity = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("ServiceType no encontrado: " + id));
        mapper.updateEntity(entity, dto);
        return mapper.toDto(repo.save(entity));
    }

    @Override
    @Transactional
    public ServiceTypeResponseDto updateStatus(Long id, ServiceTypeStatusUpdateDto dto) {
        ServiceTypeEntity entity = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("ServiceType no encontrado: " + id));
        entity.setEnabled(dto.getEnabled());
        return mapper.toDto(repo.save(entity));
    }

    @Override
    public ServiceTypeResponseDto getById(Long id) {
        return repo.findById(id).map(mapper::toDto)
                .orElseThrow(() -> new RuntimeException("ServiceType no encontrado: " + id));
    }

    @Override
    public List<ServiceTypeResponseDto> listAll() {
        return repo.findAll().stream().map(mapper::toDto).toList();
    }

    @Override
    public List<ServiceTypeResponseDto> listEnabled() {
        return repo.findAllByEnabledTrue().stream().map(mapper::toDto).toList();
    }
}
