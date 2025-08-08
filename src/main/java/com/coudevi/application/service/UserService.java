package com.coudevi.application.service;

import com.coudevi.web.dto.UserRequestDto;
import com.coudevi.web.dto.UserResponseDto;
import com.coudevi.web.dto.UserStatusUpdateDto;

import java.util.List;

public interface UserService {
    UserResponseDto createUser(UserRequestDto dto);
    List<UserResponseDto> getAllUsers();
    UserResponseDto getUserById(Long id);
    UserResponseDto updateUserStatus(Long id, UserStatusUpdateDto dto);

    UserResponseDto disableUser(Long id);           // enabled=false
    // opcionalmente, para reactivar:
    UserResponseDto enableUser(Long id);            // enabled=true
}
