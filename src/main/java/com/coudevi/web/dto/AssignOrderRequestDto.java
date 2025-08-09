package com.coudevi.web.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AssignOrderRequestDto {
    @NotNull
    private Long workerUserId;
}
