package com.coudevi.application.service;

import com.coudevi.web.dto.OrderDeliverRequestDto;
import com.coudevi.web.dto.OrderResponseDto;

public interface OrderProgressService {
    OrderResponseDto complete(Long orderId, String currentUsername);
    OrderResponseDto deliver(Long orderId, String currentUsername, OrderDeliverRequestDto dto);
}
