package com.coudevi.web.controller;

import com.coudevi.application.service.ServiceTypeService;
import com.coudevi.web.dto.ServiceTypeRequestDto;
import com.coudevi.web.dto.ServiceTypeResponseDto;
import com.coudevi.web.dto.ServiceTypeStatusUpdateDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/service-types")
@RequiredArgsConstructor
public class ServiceTypeController {
    private final ServiceTypeService service;
    @PostMapping
    public ResponseEntity<ServiceTypeResponseDto> create(@Valid @RequestBody ServiceTypeRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }
    @PutMapping("/{id}")
    public ResponseEntity<ServiceTypeResponseDto> update(@PathVariable Long id,
                                                         @Valid @RequestBody ServiceTypeRequestDto dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }
    @PatchMapping("/{id}/status")
    public ResponseEntity<ServiceTypeResponseDto> updateStatus(@PathVariable Long id,
                                                               @Valid @RequestBody ServiceTypeStatusUpdateDto dto) {
        return ResponseEntity.ok(service.updateStatus(id, dto));
    }
    @GetMapping("/{id}")
    public ResponseEntity<ServiceTypeResponseDto> get(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }
    @GetMapping
    public ResponseEntity<List<ServiceTypeResponseDto>> listAll() {
        return ResponseEntity.ok(service.listAll());
    }
    @GetMapping("/enabled")
    public ResponseEntity<List<ServiceTypeResponseDto>> listEnabled() {
        return ResponseEntity.ok(service.listEnabled());
    }
}
