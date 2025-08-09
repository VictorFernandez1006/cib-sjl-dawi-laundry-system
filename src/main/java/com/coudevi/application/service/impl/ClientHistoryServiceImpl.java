package com.coudevi.application.service.impl;

import com.coudevi.application.mapper.OrderMapper;
import com.coudevi.application.service.ClientHistoryService;
import com.coudevi.application.spec.OrderSpecifications;
import com.coudevi.domain.model.ClientEntity;
import com.coudevi.domain.model.OrderEntity;
import com.coudevi.domain.repository.ClientRepository;
import com.coudevi.domain.repository.OrderRepository;
import com.coudevi.web.dto.OrderHistoryFilterDto;
import com.coudevi.web.dto.OrderResponseDto;
import com.coudevi.web.dto.PagedResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class ClientHistoryServiceImpl implements ClientHistoryService {
    private final ClientRepository clientRepository;
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    @Override
    public PagedResponse<OrderResponseDto> myOrders(String currentUsername, OrderHistoryFilterDto filters, Pageable pageable) {
        ClientEntity client = clientRepository.findByUserUsername(currentUsername)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado para el usuario actual"));

        Specification<OrderEntity> spec =
                Specification.allOf(OrderSpecifications.byClientId(client.getId()));
        if (filters != null) {
            LocalDate from = filters.getFrom();
            LocalDate to   = filters.getTo();

            if (from != null) {
                spec = spec.and(OrderSpecifications.createdFrom(from.atStartOfDay()));
            }
            if (to != null) {
                // inclusive hasta fin de día
                spec = spec.and(OrderSpecifications.createdTo(to.atTime(23, 59, 59)));
            }
            if (filters.getStatus() != null) {
                spec = spec.and(OrderSpecifications.byStatus(filters.getStatus()));
            }
        }

        Page<OrderResponseDto> page = orderRepository.findAll(spec, pageable)
                .map(orderMapper::toDto);

        return new PagedResponse<>(
                page.getContent(),
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages()
        );
    }
}
