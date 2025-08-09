package com.coudevi.application.service.impl;

import com.coudevi.application.mapper.OrderMapper;
import com.coudevi.application.service.OrderProgressService;
import com.coudevi.domain.model.OrderEntity;
import com.coudevi.domain.model.OrderStatus;
import com.coudevi.domain.model.UserEntity;
import com.coudevi.domain.repository.OrderRepository;
import com.coudevi.domain.repository.UserRepository;
import com.coudevi.web.dto.OrderDeliverRequestDto;
import com.coudevi.web.dto.OrderResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class OrderProgressServiceImpl implements OrderProgressService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final OrderMapper orderMapper;
    @Override
    @Transactional
    public OrderResponseDto complete(Long orderId, String currentUsername) {
        UserEntity worker = userRepository.findByUsername(currentUsername)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        boolean isWorker = worker.getRoles().stream().anyMatch(r -> "TRABAJADOR".equalsIgnoreCase(r.getName()));
        if (!isWorker) {
            throw new RuntimeException("No tienes rol TRABAJADOR");
        }
        if (Boolean.FALSE.equals(worker.getEnabled())) {
            throw new RuntimeException("Usuario deshabilitado");
        }

        OrderEntity order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Orden no encontrada"));
        if (order.getStatus() != OrderStatus.EN_PROCESO)
            throw new RuntimeException("Solo se puede finalizar una orden EN_PROCESO");
        if (order.getAssignedTo() == null || !order.getAssignedTo().getId().equals(worker.getId()))
            throw new RuntimeException("No estás asignado a esta orden");
        order.setStatus(OrderStatus.TERMINADO);
        order.setFinishedAt(LocalDateTime.now());
        order.setFinishedBy(worker);

        return orderMapper.toDto(orderRepository.save(order));
    }

    @Override
    @Transactional
    public OrderResponseDto deliver(Long orderId, String currentUsername, OrderDeliverRequestDto dto) {
        UserEntity staff = userRepository.findByUsername(currentUsername)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        boolean isReception = staff.getRoles().stream().anyMatch(r -> "RECEPCIONISTA".equalsIgnoreCase(r.getName()));
        boolean isAdmin = staff.getRoles().stream().anyMatch(r -> "ADMIN".equalsIgnoreCase(r.getName()));
        if (!(isReception || isAdmin)) {
            throw new RuntimeException("No tienes permisos para entregar");
        }
        if (Boolean.FALSE.equals(staff.getEnabled())) {
            throw new RuntimeException("Usuario deshabilitado");
        }

        OrderEntity order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Orden no encontrada"));
        if (order.getStatus() != OrderStatus.TERMINADO){
            throw new RuntimeException("Solo se puede entregar una orden TERMINADA");
        }
        order.setStatus(OrderStatus.ENTREGADO);
        order.setDeliveredAt(LocalDateTime.now());
        order.setDeliveredBy(staff);
        order.setReceiverName(dto != null ? dto.getReceiverName() : null);
        return orderMapper.toDto(orderRepository.save(order));
    }
}
