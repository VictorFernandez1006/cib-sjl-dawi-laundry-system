package com.coudevi.web.controller;

import com.coudevi.application.service.UserService;
import com.coudevi.web.dto.UserRequestDto;
import com.coudevi.web.dto.UserResponseDto;
import com.coudevi.web.dto.UserStatusUpdateDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @PostMapping
    public ResponseEntity<UserResponseDto> create(@Valid @RequestBody UserRequestDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(dto));
    }
    @GetMapping
    public ResponseEntity<List<UserResponseDto>> findAll(){
        return ResponseEntity.ok(userService.getAllUsers());
    }
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> findById(@PathVariable Long id){
        return ResponseEntity.ok(userService.getUserById(id));
    }
    @PatchMapping("/{id}/status")
    public ResponseEntity<UserResponseDto> updateStatus(@PathVariable Long id,
                                                        @Valid @RequestBody UserStatusUpdateDto dto){
        return ResponseEntity.ok(userService.updateUserStatus(id, dto));
    }

    @PatchMapping("/{id}/disble")
    public ResponseEntity<UserResponseDto> disable(@PathVariable Long id){
        return ResponseEntity.ok(userService.disableUser(id));
    }
    @PatchMapping("/{id}/enable")
    public ResponseEntity<UserResponseDto> enable(@PathVariable Long id){
        return ResponseEntity.ok(userService.enableUser(id));
    }
}
