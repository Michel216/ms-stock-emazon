package com.emazon.stockService.domain.api;

import com.emazon.stockService.domain.model.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ArticleServicePort {
    Article createArticle(Article article);
    Article getArticleById(Long id);
    Page<Article> listArticles(String name, Long categoryId, Long brandId, String sortBy, String sortDirection, Pageable pageable);
    Article updateArticle(Long id, Article article);
}
