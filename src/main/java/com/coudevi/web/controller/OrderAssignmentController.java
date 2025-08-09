package com.coudevi.web.controller;

import com.coudevi.application.service.OrderAssignmentService;
import com.coudevi.web.dto.AssignOrderRequestDto;
import com.coudevi.web.dto.OrderResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderAssignmentController {
    private final OrderAssignmentService service;
    @GetMapping("/available")
    public ResponseEntity<List<OrderResponseDto>> listAvailable() {
        return ResponseEntity.ok(service.listAvailable());
    }
    @PostMapping("/{id}/take")
    public ResponseEntity<OrderResponseDto> take(@PathVariable Long id, Authentication auth) {
        String currentUsername = auth.getName();
        return ResponseEntity.ok(service.takeOrder(id, currentUsername));
    }
    @PatchMapping("/{id}/assign")
    public ResponseEntity<OrderResponseDto> assign(@PathVariable Long id,
                                                   @Valid @RequestBody AssignOrderRequestDto dto) {
        return ResponseEntity.ok(service.assignOrder(id, dto));
    }
    @GetMapping("/my/active")
    public ResponseEntity<List<OrderResponseDto>> myActive(Authentication auth) {
        String currentUsername = auth.getName();
        return ResponseEntity.ok(service.myActiveOrders(currentUsername));
    }
}
