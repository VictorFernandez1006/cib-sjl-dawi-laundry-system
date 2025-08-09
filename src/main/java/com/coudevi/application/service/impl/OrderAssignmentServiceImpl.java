package com.coudevi.application.service.impl;

import com.coudevi.application.mapper.OrderMapper;
import com.coudevi.application.service.OrderAssignmentService;
import com.coudevi.domain.model.OrderEntity;
import com.coudevi.domain.model.OrderStatus;
import com.coudevi.domain.model.UserEntity;
import com.coudevi.domain.repository.OrderRepository;
import com.coudevi.domain.repository.UserRepository;
import com.coudevi.web.dto.AssignOrderRequestDto;
import com.coudevi.web.dto.OrderResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.EnumSet;
import java.util.List;
@Service
@RequiredArgsConstructor
public class OrderAssignmentServiceImpl implements OrderAssignmentService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final OrderMapper orderMapper;
    @Override
    @Transactional
    public OrderResponseDto takeOrder(Long orderId, String currentUsername) {
        UserEntity worker = userRepository.findByUsername(currentUsername)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        if (Boolean.FALSE.equals(worker.getEnabled())) {
            throw new RuntimeException("Trabajador deshabilitado");
        }
        boolean isWorker = worker.getRoles().stream().anyMatch(r -> "TRABAJADOR".equalsIgnoreCase(r.getName()));
        if (!isWorker) {
            throw new RuntimeException("No tienes el rol TRABAJADOR");
        }
        OrderEntity order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Orden no encontrada"));
        if (order.getAssignedTo() != null) {
            throw new RuntimeException("La orden ya está asignada");
        }
        if (order.getStatus() != OrderStatus.REGISTRADO) {
            throw new RuntimeException("La orden no está disponible para tomar");
        }
        order.setAssignedTo(worker);
        order.setStatus(OrderStatus.EN_PROCESO);
        return orderMapper.toDto(orderRepository.save(order));
    }

    @Override
    @Transactional
    public OrderResponseDto assignOrder(Long orderId, AssignOrderRequestDto dto) {
        OrderEntity order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Orden no encontrada"));
        if (order.getAssignedTo() != null) {
            throw new RuntimeException("La orden ya está asignada");
        }
        if (order.getStatus() != OrderStatus.REGISTRADO) {
            throw new RuntimeException("La orden no está disponible para asignar");
        }
        UserEntity worker = userRepository.findById(dto.getWorkerUserId())
                .orElseThrow(() -> new RuntimeException("Trabajador no encontrado"));

        if (Boolean.FALSE.equals(worker.getEnabled())) {
            throw new RuntimeException("Trabajador deshabilitado");
        }
        boolean isWorker = worker.getRoles().stream().anyMatch(r -> "TRABAJADOR".equalsIgnoreCase(r.getName()));
        if (!isWorker) {
            throw new RuntimeException("El usuario no tiene rol TRABAJADOR");
        }
        order.setAssignedTo(worker);
        order.setStatus(OrderStatus.EN_PROCESO);

        return orderMapper.toDto(orderRepository.save(order));
    }

    @Override
    public List<OrderResponseDto> listAvailable() {
        return orderRepository.findByStatusAndAssignedToIsNull(OrderStatus.REGISTRADO)
                .stream()
                .map(orderMapper::toDto)
                .toList();
    }

    @Override
    public List<OrderResponseDto> myActiveOrders(String currentUsername) {
        UserEntity worker = userRepository.findByUsername(currentUsername)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        return orderRepository.findByAssignedTo_IdAndStatusIn(
                        worker.getId(),
                        EnumSet.of(OrderStatus.EN_PROCESO, OrderStatus.REGISTRADO))
                .stream()
                .map(orderMapper::toDto)
                .toList();
    }
}
