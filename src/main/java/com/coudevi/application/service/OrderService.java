package com.coudevi.application.service;

import com.coudevi.web.dto.OrderCreateRequestDto;
import com.coudevi.web.dto.OrderResponseDto;

public interface OrderService {
    OrderResponseDto registerIntake(OrderCreateRequestDto dto);
}
