package com.coba.spring.coba_crud_spring.service;

import com.coba.spring.coba_crud_spring.dto.LoginRequestDTO;
import com.coba.spring.coba_crud_spring.dto.RegisterRequestDTO;

public interface AuthService {
    String login(LoginRequestDTO loginRequest);

    String register(RegisterRequestDTO registerRequest);
}
