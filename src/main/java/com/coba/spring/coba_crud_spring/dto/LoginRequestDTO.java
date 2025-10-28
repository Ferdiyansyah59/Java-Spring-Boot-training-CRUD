package com.coba.spring.coba_crud_spring.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor // buat bikin constructor tanpa argument
@AllArgsConstructor // buat bikin constructor dengan argument lengkap
public class LoginRequestDTO {
    @NotBlank(message = "Username or Email is required")
    private String usernameOrEmail;

    @NotBlank(message = "Password is required")
    private String password;
}
