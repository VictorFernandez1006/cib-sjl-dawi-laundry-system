package com.coudevi.application.service.impl;

import com.coudevi.application.mapper.UserMapper;
import com.coudevi.application.service.UserService;
import com.coudevi.domain.model.RoleEntity;
import com.coudevi.domain.model.UserEntity;
import com.coudevi.domain.repository.RoleRepository;
import com.coudevi.domain.repository.UserRepository;
import com.coudevi.web.dto.UserRequestDto;
import com.coudevi.web.dto.UserResponseDto;
import com.coudevi.web.dto.UserStatusUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    @Override
    @Transactional
    public UserResponseDto createUser(UserRequestDto requestDto) {
        RoleEntity role = roleRepository.findById(requestDto.getRoleId())
                .orElseThrow(() -> new RuntimeException("Role not found"));
        String encodePassword = passwordEncoder.encode(requestDto.getPassword());
        UserEntity entity = userMapper.toEntity(requestDto, role);
        entity.setPassword(encodePassword);
        return userMapper.toDto(userRepository.save(entity));
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserResponseDto> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponseDto getUserById(Long id) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return userMapper.toDto(user);
    }

    @Override
    @Transactional
    public UserResponseDto updateUserStatus(Long id, UserStatusUpdateDto dto) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setEnabled(dto.getEnabled());
        return userMapper.toDto(user);
    }

    @Override
    @Transactional
    public UserResponseDto disableUser(Long id) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        if (Boolean.FALSE.equals(user.getEnabled())){
            return userMapper.toDto(user);
        }
        user.setEnabled(false);
        return userMapper.toDto(userRepository.save(user));
    }

    @Override
    @Transactional
    public UserResponseDto enableUser(Long id) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found: " + id));

        if (Boolean.TRUE.equals(user.getEnabled())) {
            return userMapper.toDto(user);
        }

        user.setEnabled(true);
        return userMapper.toDto(userRepository.save(user));
    }
}
