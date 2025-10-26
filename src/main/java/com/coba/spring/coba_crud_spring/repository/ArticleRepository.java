package com.coba.spring.coba_crud_spring.repository;

import com.coba.spring.coba_crud_spring.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
    List<Article> findByPublished(Boolean published);

    List<Article> findByAuthor(String author);

    List<Article> findByTitleArticleContaining(String keyword);

    @Query("SELECT a FROM Article a WHERE a.published = true ORDER BY a.createdAt DESC")
    List<Article> findPublishedArticlesOrderByDateDesc();

}
