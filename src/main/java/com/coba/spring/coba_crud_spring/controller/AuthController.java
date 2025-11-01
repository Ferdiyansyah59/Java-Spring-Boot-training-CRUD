package com.coba.spring.coba_crud_spring.controller;

import com.coba.spring.coba_crud_spring.dto.*;
import com.coba.spring.coba_crud_spring.model.User;
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
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<SuccessResponse<AuthResponseDTO>> authenticateUser(@Valid @RequestBody LoginRequestDTO loginRequest) {
        String token = authService.login(loginRequest);
        AuthResponseDTO authResponse = new AuthResponseDTO(token);

        SuccessResponse<AuthResponseDTO> res = new SuccessResponse<>(
                HttpStatus.OK.value(),
                "Login Successful",
                authResponse
        );

        return ResponseEntity.ok(res);
    }

    @PostMapping("/register")
    public ResponseEntity<SuccessResponse<UserResponseDTO>> registerUser(@Valid @RequestBody RegisterRequestDTO registerRequesrt) {
        User newUser = authService.register(registerRequesrt);

        UserResponseDTO userResponse = new UserResponseDTO();
        userResponse.setId(newUser.getId());
        userResponse.setName(newUser.getName());
        userResponse.setUsername(newUser.getUsername());
        userResponse.setEmail(newUser.getEmail());
        userResponse.setRole(newUser.getRole());

        SuccessResponse<UserResponseDTO> res = new SuccessResponse<>(
                HttpStatus.CREATED.value(),
                "User Registered Successfuly",
                userResponse
        );

        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }
}
