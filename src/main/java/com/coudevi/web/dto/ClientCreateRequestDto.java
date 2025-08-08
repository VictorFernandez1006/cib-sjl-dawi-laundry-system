package com.coudevi.web.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientCreateRequestDto {

    @NotBlank
    private String documentId;
    @NotBlank private String fullName;
    private String phone;
    @Email
    private String email;
    private String address;

    @NotBlank private String username;
    @NotBlank private String password;
}
