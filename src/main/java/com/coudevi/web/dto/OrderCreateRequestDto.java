package com.coudevi.web.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderCreateRequestDto {
    @NotNull private Long clientId;
    @NotNull private Long serviceTypeId;
    @NotNull
    @DecimalMin(value = "0.01", message = "El peso debe ser mayor a 0")
    private BigDecimal weightKg;
    private String notes;
}
