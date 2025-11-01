package com.coba.spring.coba_crud_spring.service;

import com.coba.spring.coba_crud_spring.dto.LoginRequestDTO;
import com.coba.spring.coba_crud_spring.dto.RegisterRequestDTO;
import com.coba.spring.coba_crud_spring.model.User;

public interface AuthService {
    String login(LoginRequestDTO loginRequest);

    User register(RegisterRequestDTO registerRequest);
}
