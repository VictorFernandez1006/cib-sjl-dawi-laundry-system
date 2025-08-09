package com.coudevi.application.service;

import com.coudevi.web.dto.AssignOrderRequestDto;
import com.coudevi.web.dto.OrderResponseDto;

import java.util.List;

public interface OrderAssignmentService {
    OrderResponseDto takeOrder(Long orderId, String currentUsername);
    OrderResponseDto assignOrder(Long orderId, AssignOrderRequestDto dto);
    List<OrderResponseDto> listAvailable();
    List<OrderResponseDto> myActiveOrders(String currentUsername);
}
