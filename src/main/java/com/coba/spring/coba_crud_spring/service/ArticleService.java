package com.coba.spring.coba_crud_spring.service;

import com.coba.spring.coba_crud_spring.dto.ArticleRequestDTO;
import com.coba.spring.coba_crud_spring.dto.ArticleResponseDTO;
import java.util.List;

public interface ArticleService {
    ArticleResponseDTO createArticle(ArticleRequestDTO requestDTO);
    ArticleResponseDTO getArticleById(Long id);
    List<ArticleResponseDTO> getAllArticle();
    ArticleResponseDTO updateArticle(Long id, ArticleRequestDTO requestDTO);
    void deleteArticle(Long id);
    List<ArticleResponseDTO> getPublishedArticles();
}
