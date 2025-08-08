package com.coudevi.web.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientResponseDto {
    private Long id;
    private String documentId;
    private String fullName;
    private String phone;
    private String email;
    private String address;
    private Long userId;
    private String username;
    private boolean enabled;
}
