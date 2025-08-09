package com.coudevi.web.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServiceTypeRequestDto {
    @NotBlank private String name;
    @NotNull
    @DecimalMin("0.01") private BigDecimal pricePerKg;
}
