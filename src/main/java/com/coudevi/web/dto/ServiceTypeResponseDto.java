package com.coudevi.web.dto;

import lombok.*;

import java.math.BigDecimal;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServiceTypeResponseDto {
    private Long id;
    private String name;
    private BigDecimal pricePerKg;
    private boolean enabled;
}
