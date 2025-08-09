package com.coudevi.web.controller;

import com.coudevi.application.service.OrderService;
import com.coudevi.web.dto.OrderCreateRequestDto;
import com.coudevi.web.dto.OrderResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    @PostMapping
    public ResponseEntity<OrderResponseDto> create(@Valid @RequestBody OrderCreateRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.registerIntake(dto));
    }
}
