package com.coba.spring.coba_crud_spring.dto;

import lombok.Data;

@Data
public class AuthResponseDTO {
    private String accessToken;
    private String tokenType = "Bearer";

/* Kalo mau lebih clear bisa pake @RequiredArgsConstructor dan
properties accessToken di final in. Sama aja sama manual kek gini. */
    public AuthResponseDTO(String accessToken) {
        this.accessToken = accessToken;
    }
}
