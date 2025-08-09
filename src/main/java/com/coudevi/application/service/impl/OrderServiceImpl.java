package com.coudevi.application.service.impl;

import com.coudevi.application.mapper.OrderMapper;
import com.coudevi.application.service.OrderService;
import com.coudevi.domain.model.ClientEntity;
import com.coudevi.domain.model.OrderEntity;
import com.coudevi.domain.model.ServiceTypeEntity;
import com.coudevi.domain.repository.ClientRepository;
import com.coudevi.domain.repository.OrderRepository;
import com.coudevi.domain.repository.ServiceTypeRepository;
import com.coudevi.web.dto.OrderCreateRequestDto;
import com.coudevi.web.dto.OrderResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final ClientRepository clientRepository;
    private final ServiceTypeRepository serviceTypeRepository;
    private final OrderMapper orderMapper;
    @Override
    @Transactional
    public OrderResponseDto registerIntake(OrderCreateRequestDto dto) {
        ClientEntity client = clientRepository.findById(dto.getClientId())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        if (client.getUser() == null || Boolean.FALSE.equals(client.getUser().getEnabled())){
            throw new RuntimeException("El cliente está inhabilitado o sin cuenta");
        }
        ServiceTypeEntity serviceTypeEntity = serviceTypeRepository.findById(dto.getServiceTypeId())
                .orElseThrow(() -> new RuntimeException("Tipo de servicio no encontrado"));
        if (Boolean.FALSE.equals(serviceTypeEntity.getEnabled())) {
            throw new RuntimeException("El tipo de servicio está deshabilitado");
        }

        if (dto.getWeightKg().compareTo(new BigDecimal("0.00")) <= 0) {
            throw new RuntimeException("El peso debe ser mayor a 0");
        }

        BigDecimal unit = serviceTypeEntity.getPricePerKg();
        BigDecimal total = unit.multiply(dto.getWeightKg());
        OrderEntity entity = orderMapper.toEntity(dto, client, serviceTypeEntity, unit, total);
        OrderEntity saved = orderRepository.save(entity);
        return orderMapper.toDto(saved);
    }
}
