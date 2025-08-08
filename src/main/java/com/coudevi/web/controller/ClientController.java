package com.coudevi.web.controller;

import com.coudevi.application.service.ClientService;
import com.coudevi.web.dto.ClientCreateRequestDto;
import com.coudevi.web.dto.ClientResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/clientes")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @PostMapping
    public ResponseEntity<ClientResponseDto> register(@Valid @RequestBody ClientCreateRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(clientService.register(dto));
    }
}
