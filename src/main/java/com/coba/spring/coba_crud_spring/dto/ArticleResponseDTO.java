package com.coba.spring.coba_crud_spring.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleResponseDTO {
    private Long id;
    private String titleArticle;
    private String contentArticle;
    private String author;
    private Boolean published;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
