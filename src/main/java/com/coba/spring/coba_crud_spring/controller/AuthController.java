package com.coba.spring.coba_crud_spring.controller;

import com.coba.spring.coba_crud_spring.dto.AuthResponseDTO;
import com.coba.spring.coba_crud_spring.dto.LoginRequestDTO;
import com.coba.spring.coba_crud_spring.dto.RegisterRequestDTO;
import com.coba.spring.coba_crud_spring.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/apu/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> authenticateUser(@Valid @RequestBody LoginRequestDTO loginRequest) {
        String token = authService.login(loginRequest);
        AuthResponseDTO authResponse = new AuthResponseDTO(token);
        return ResponseEntity.ok(authResponse);
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequestDTO registerRequesrt) {
        String res = authService.register(registerRequesrt);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }
}
