package com.coudevi.application.mapper.impl;

import com.coudevi.application.mapper.OrderMapper;
import com.coudevi.domain.model.ClientEntity;
import com.coudevi.domain.model.OrderEntity;
import com.coudevi.domain.model.OrderStatus;
import com.coudevi.domain.model.ServiceTypeEntity;
import com.coudevi.web.dto.OrderCreateRequestDto;
import com.coudevi.web.dto.OrderResponseDto;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Component
public class OrderMapperImpl implements OrderMapper {
    @Override
    public OrderEntity toEntity(OrderCreateRequestDto dto, ClientEntity client, ServiceTypeEntity serviceTypeEntity, BigDecimal unitPrice, BigDecimal totalPrice) {
        return OrderEntity.builder()
                .client(client)
                .serviceType(serviceTypeEntity)
                .weightKg(dto.getWeightKg())
                .unitPrice(unitPrice)
                .totalPrice(totalPrice)
                .status(OrderStatus.REGISTRADO)
                .notes(dto.getNotes())
                .assignedTo(null)
                .createdAt(LocalDateTime.now())
                .build();
    }

    @Override
    public OrderResponseDto toDto(OrderEntity entity) {
        return OrderResponseDto.builder()
                .id(entity.getId())
                .clientId(entity.getClient().getId())
                .clientName(entity.getClient().getFullName())
                .serviceTypeId(entity.getServiceType().getId())
                .serviceName(entity.getServiceType().getName())
                .weightKg(entity.getWeightKg())
                .unitPrice(entity.getUnitPrice())
                .totalPrice(entity.getTotalPrice())
                .status(entity.getStatus())
                .notes(entity.getNotes())
                .assignedToUserId(entity.getAssignedTo() != null ? entity.getAssignedTo().getId() : null)
                .createdAt(entity.getCreatedAt())
                .build();
    }
}
