package com.coudevi.web.controller;

import com.coudevi.application.service.ClientHistoryService;
import com.coudevi.domain.model.OrderStatus;
import com.coudevi.web.dto.OrderHistoryFilterDto;
import com.coudevi.web.dto.OrderResponseDto;
import com.coudevi.web.dto.PagedResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/client")
@RequiredArgsConstructor
public class ClientHistoryController {
    private final ClientHistoryService historyService;
    @GetMapping("/orders")
    public ResponseEntity<PagedResponse<OrderResponseDto>> myOrders(
            Authentication auth,
            @RequestParam(required = false) LocalDate from,
            @RequestParam(required = false) LocalDate to,
            @RequestParam(required = false) OrderStatus status,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "createdAt,DESC") String sort
    ) {
        String[] sortParts = sort.split(",");
        Sort.Direction dir = sortParts.length > 1 && "ASC".equalsIgnoreCase(sortParts[1])
                ? Sort.Direction.ASC : Sort.Direction.DESC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(dir, sortParts[0]));

        OrderHistoryFilterDto filters = new OrderHistoryFilterDto(from, to, status);

        var response = historyService.myOrders(auth.getName(), filters, pageable);
        return ResponseEntity.ok(response);
    }
}
