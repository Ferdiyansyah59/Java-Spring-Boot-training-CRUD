package com.coba.spring.coba_crud_spring.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;
import jakarta.persistence.Lob;

@Entity
@Table(name="article")
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title_article", nullable = false, length = 256)
    private String titleArticle;

    @Lob // ini bilang ke JPA "Pake Tipe data TEXT/CLOB"
    @Column(name = "content_article", nullable = false)
    private String contentArticle;

    @Column(nullable = false, length = 100)
    private String author;

    @Column(nullable = false)
    private Boolean published = false;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

}
