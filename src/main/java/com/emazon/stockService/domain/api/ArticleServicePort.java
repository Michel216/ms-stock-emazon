package com.emazon.stockService.domain.api;

import com.emazon.stockService.domain.model.Article;

public interface ArticleServicePort {
    Article createArticle(Article article);
    Article getArticleById(Long id);
}
