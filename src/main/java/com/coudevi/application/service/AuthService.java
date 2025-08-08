package com.coudevi.application.service;

import com.coudevi.web.dto.LoginRequest;
import com.coudevi.web.dto.LoginResponse;

public interface AuthService {
    LoginResponse authenticate(LoginRequest request);
}
