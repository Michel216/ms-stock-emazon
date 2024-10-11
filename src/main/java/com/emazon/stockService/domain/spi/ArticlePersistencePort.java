package com.emazon.stockService.domain.spi;

import com.emazon.stockService.domain.model.Article;

import java.util.Optional;

public interface ArticlePersistencePort {
    Article saveArticle(Article article);
    Optional<Article> findById(Long id);
}