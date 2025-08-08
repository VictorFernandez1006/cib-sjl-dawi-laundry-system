package com.coudevi.application.service.impl;

import com.coudevi.application.mapper.ClientMapper;
import com.coudevi.application.mapper.UserMapper;
import com.coudevi.application.service.ClientService;
import com.coudevi.domain.model.ClientEntity;
import com.coudevi.domain.model.RoleEntity;
import com.coudevi.domain.model.UserEntity;
import com.coudevi.domain.repository.ClientRepository;
import com.coudevi.domain.repository.RoleRepository;
import com.coudevi.domain.repository.UserRepository;
import com.coudevi.web.dto.ClientCreateRequestDto;
import com.coudevi.web.dto.ClientResponseDto;
import com.coudevi.web.dto.UserRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;
    private final ClientMapper clientMapper;
    private final PasswordEncoder passwordEncoder;
    @Override
    public ClientResponseDto register(ClientCreateRequestDto dto) {
        if (clientRepository.existsByDocumentId(dto.getDocumentId())){
            throw new RuntimeException("El documento ya está registrado");
        }
        if (dto.getEmail() != null && clientRepository.existsByEmail(dto.getEmail())){
            throw new RuntimeException("El email ya está registrado");
        }
        if (userRepository.existsByUsername(dto.getUsername())){
            throw new RuntimeException("El username ya está en uso");
        }
        RoleEntity clienteRole = roleRepository.findByName("CLIENTE")
                .orElseThrow(() -> new RuntimeException("Rol CLIENTE no encontrado"));
        UserRequestDto requestDto = UserRequestDto.builder()
                .username(dto.getUsername())
                .password(passwordEncoder.encode(dto.getPassword()))
                .email(dto.getEmail())
                .fullName(dto.getFullName())
                .build();
        UserEntity user = userMapper.toEntity(requestDto, clienteRole);

        UserEntity savedUser = userRepository.save(user);
        ClientEntity client = clientMapper.toEntity(dto, savedUser);
        ClientEntity savedClient = clientRepository.save(client);
        return clientMapper.toDto(savedClient);

    }
}
