package com.coba.spring.coba_crud_spring.controller;


import com.coba.spring.coba_crud_spring.dto.ArticleRequestDTO;
import com.coba.spring.coba_crud_spring.dto.ArticleResponseDTO;
import com.coba.spring.coba_crud_spring.service.ArticleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/article")
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService articleService;

    @PostMapping
    public ResponseEntity<ArticleResponseDTO> createArticle(@Valid @RequestBody ArticleRequestDTO requestDTO) {
        ArticleResponseDTO createdArticle = articleService.createArticle(requestDTO);
        return new ResponseEntity<>(createdArticle, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ArticleResponseDTO>> getAllArticles() {
        List<ArticleResponseDTO> articles = articleService.getAllArticle();
        return ResponseEntity.ok(articles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArticleResponseDTO> getArticleById(@PathVariable Long id) {
        ArticleResponseDTO article = articleService.getArticleById(id);
        return ResponseEntity.ok(article);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ArticleResponseDTO> updateArticle (
            @PathVariable Long id,
            @Valid @RequestBody ArticleRequestDTO requestDTO
    ) {
        ArticleResponseDTO updatedArticle = articleService.updateArticle(id, requestDTO);
        return ResponseEntity.ok(updatedArticle);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable Long id) {
        articleService.deleteArticle(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/published")
    public ResponseEntity<List<ArticleResponseDTO>> getPublishedArticles() {
        List<ArticleResponseDTO> article = articleService.getPublishedArticles();
        return ResponseEntity.ok(article);
    }
}
