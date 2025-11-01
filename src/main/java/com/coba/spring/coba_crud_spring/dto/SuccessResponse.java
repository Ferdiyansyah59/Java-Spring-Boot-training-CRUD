package com.coba.spring.coba_crud_spring.dto;
import lombok.Data;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class SuccessResponse<T> {
    private int status;
    private String message;
    private LocalDateTime timestamp;
    private T data;

    public SuccessResponse(int status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
        this.timestamp = LocalDateTime.now();
    }
}
