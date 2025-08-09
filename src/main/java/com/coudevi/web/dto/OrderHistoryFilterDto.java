package com.coudevi.web.dto;

import com.coudevi.domain.model.OrderStatus;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderHistoryFilterDto {
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) private LocalDate from;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) private LocalDate to;
    private OrderStatus status;
}
