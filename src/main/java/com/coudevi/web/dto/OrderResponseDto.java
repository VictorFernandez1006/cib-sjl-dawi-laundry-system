package com.coudevi.web.dto;

import com.coudevi.domain.model.OrderStatus;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderResponseDto {
    private Long id;
    private Long clientId;
    private String clientName;
    private Long serviceTypeId;
    private String serviceName;
    private BigDecimal weightKg;
    private BigDecimal unitPrice;
    private BigDecimal totalPrice;
    private OrderStatus status;
    private String notes;
    private Long assignedToUserId;
    private LocalDateTime createdAt;
}
