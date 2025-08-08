package com.coudevi.web.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserStatusUpdateDto {
    @NotNull(message = "El estado es obligatorio")
    private Boolean enabled;
}
