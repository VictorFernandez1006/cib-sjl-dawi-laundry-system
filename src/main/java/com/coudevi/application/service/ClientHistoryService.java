package com.coudevi.application.service;

import com.coudevi.web.dto.OrderHistoryFilterDto;
import com.coudevi.web.dto.OrderResponseDto;
import com.coudevi.web.dto.PagedResponse;
import org.springframework.data.domain.Pageable;

public interface ClientHistoryService {
    PagedResponse<OrderResponseDto> myOrders(String currentUsername,
                                             OrderHistoryFilterDto filters,
                                             Pageable pageable);
}
