package com.coudevi.web.controller;

import com.coudevi.application.service.OrderProgressService;
import com.coudevi.web.dto.OrderDeliverRequestDto;
import com.coudevi.web.dto.OrderResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderProgressController {
    private final OrderProgressService service;
    @PatchMapping("/{id}/complete")
    public ResponseEntity<OrderResponseDto> complete(@PathVariable Long id, Authentication auth) {
        return ResponseEntity.ok(service.complete(id, auth.getName()));
    }
    @PatchMapping("/{id}/deliver")
    public ResponseEntity<OrderResponseDto> deliver(@PathVariable Long id,
                                                    Authentication auth,
                                                    @Valid @RequestBody(required = false) OrderDeliverRequestDto dto) {
        return ResponseEntity.ok(service.deliver(id, auth.getName(), dto));
    }
}
