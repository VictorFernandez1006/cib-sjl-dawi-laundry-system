package com.coudevi.application.mapper;

import com.coudevi.domain.model.ClientEntity;
import com.coudevi.domain.model.OrderEntity;
import com.coudevi.domain.model.ServiceTypeEntity;
import com.coudevi.web.dto.OrderCreateRequestDto;
import com.coudevi.web.dto.OrderResponseDto;

import java.math.BigDecimal;

public interface OrderMapper {
    OrderEntity toEntity(OrderCreateRequestDto dto, ClientEntity client,
                         ServiceTypeEntity serviceTypeEntity, BigDecimal unitPrice, BigDecimal totalPrice);
    OrderResponseDto toDto(OrderEntity entity);
}
