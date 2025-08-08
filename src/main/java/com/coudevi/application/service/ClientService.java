package com.coudevi.application.service;

import com.coudevi.web.dto.ClientCreateRequestDto;
import com.coudevi.web.dto.ClientResponseDto;

public interface ClientService {
    ClientResponseDto register(ClientCreateRequestDto dto);
}
