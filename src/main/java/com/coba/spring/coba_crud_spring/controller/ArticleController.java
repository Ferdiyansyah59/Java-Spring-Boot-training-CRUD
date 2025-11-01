package com.coba.spring.coba_crud_spring.controller;


import com.coba.spring.coba_crud_spring.dto.ArticleRequestDTO;
import com.coba.spring.coba_crud_spring.dto.ArticleResponseDTO;
import com.coba.spring.coba_crud_spring.dto.SuccessResponse;
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
    public ResponseEntity<SuccessResponse<ArticleResponseDTO>> createArticle(@Valid @RequestBody ArticleRequestDTO requestDTO) {
        ArticleResponseDTO createdArticle = articleService.createArticle(requestDTO);

        SuccessResponse<ArticleResponseDTO> res = new SuccessResponse<>(
                HttpStatus.CREATED.value(),
                "Article Created Successfully",
                createdArticle
        );

        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<SuccessResponse<List<ArticleResponseDTO>>> getAllArticles() {
        List<ArticleResponseDTO> articles = articleService.getAllArticle();

        SuccessResponse<List<ArticleResponseDTO>> res = new SuccessResponse<>(
                HttpStatus.OK.value(),
                "Article retrieved Successfully",
                articles
        );
        return ResponseEntity.ok(res);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SuccessResponse<ArticleResponseDTO>> getArticleById(@PathVariable Long id) {
        ArticleResponseDTO article = articleService.getArticleById(id);
        SuccessResponse<ArticleResponseDTO> res = new SuccessResponse<>(
                HttpStatus.OK.value(),
                "Article found",
                article
        );
        return ResponseEntity.ok(res);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SuccessResponse<ArticleResponseDTO>> updateArticle (
            @PathVariable Long id,
            @Valid @RequestBody ArticleRequestDTO requestDTO
    ) {
        ArticleResponseDTO updatedArticle = articleService.updateArticle(id, requestDTO);

        SuccessResponse<ArticleResponseDTO> res = new SuccessResponse<>(
                HttpStatus.CREATED.value(),
                "Article Updated Successfully!",
                updatedArticle
        );

        return ResponseEntity.ok(res);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable Long id) {
        articleService.deleteArticle(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/published")
    public ResponseEntity<SuccessResponse<List<ArticleResponseDTO>>> getPublishedArticles() {
        List<ArticleResponseDTO> article = articleService.getPublishedArticles();
        SuccessResponse<List<ArticleResponseDTO>> res = new SuccessResponse<>(
                HttpStatus.OK.value(),
                "Article Updated Successfully!",
                article
        );
        return ResponseEntity.ok(res);
    }
}
