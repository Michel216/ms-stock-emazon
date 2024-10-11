package com.emazon.stockService.domain.spi;

import com.emazon.stockService.domain.model.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ArticlePersistencePort {
    Article saveArticle(Article article);
    Optional<Article> findById(Long id);
    Page<Article> findAll(String name, Long categoryId, Long brandId, String sortBy, String sortDirection, Pageable pageable);
    Article updateArticle(Article article);
}