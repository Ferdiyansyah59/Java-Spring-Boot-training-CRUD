package com.coba.spring.coba_crud_spring.service;

import com.coba.spring.coba_crud_spring.dto.ArticleResponseDTO;
import com.coba.spring.coba_crud_spring.dto.ArticleRequestDTO;
import com.coba.spring.coba_crud_spring.exception.ResourceNotFoundException;
import com.coba.spring.coba_crud_spring.model.Article;
import com.coba.spring.coba_crud_spring.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;

    private ArticleResponseDTO mapToResponseDTO(Article article) {
        ArticleResponseDTO dto = new ArticleResponseDTO();
        dto.setId(article.getId());
        dto.setTitleArticle(article.getTitleArticle());
        dto.setContentArticle(article.getContentArticle());
        dto.setAuthor(article.getAuthor());
        dto.setPublished(article.getPublished());
        dto.setCreatedAt(article.getCreatedAt());
        dto.setUpdatedAt(article.getUpdatedAt());

        return dto;
    }


    @Override
    public ArticleResponseDTO createArticle(ArticleRequestDTO requestDTO) {
        Article article = new Article();
        article.setTitleArticle(requestDTO.getTitleArticle());
        article.setContentArticle(requestDTO.getContentArticle());
        article.setAuthor(requestDTO.getAuthor());
        article.setPublished(requestDTO.getPublished());

        Article savedArticle = articleRepository.save(article);
        return mapToResponseDTO(savedArticle);
    }

    @Override
    public ArticleResponseDTO getArticleById(Long id) {
        Article article = articleRepository.findById(id)
                .orElseThrow(()
                        -> new ResourceNotFoundException("Article not found with id: " + id));
        return mapToResponseDTO(article);
    }

    @Override
    public List<ArticleResponseDTO> getAllArticle() {
        return articleRepository.findAll()
                .stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ArticleResponseDTO updateArticle(Long id, ArticleRequestDTO requestDTO) {
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Article not found with id: " + id));

        article.setTitleArticle(requestDTO.getTitleArticle());
        article.setContentArticle(requestDTO.getContentArticle());
        article.setAuthor(requestDTO.getAuthor());
        article.setPublished(requestDTO.getPublished());

        Article updatedArticle = articleRepository.save(article);
        return mapToResponseDTO(updatedArticle);
    }

    @Override
    public void deleteArticle(Long id) {
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("article not found with id: " + id));
        articleRepository.delete(article);
    }

    @Override
    public List<ArticleResponseDTO> getPublishedArticles() {
        return articleRepository.findByPublished(true)
                .stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }
}
