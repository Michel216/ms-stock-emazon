// src/main/java/com/emazon/stockService/adapters/driven/jpa/mysql/adapter/ArticleJpaAdapter.java

package com.emazon.stockService.adapters.driven.jpa.mysql.adapter;

import com.emazon.stockService.adapters.driven.jpa.mysql.entity.ArticleEntity;
import com.emazon.stockService.adapters.driven.jpa.mysql.mapper.ArticleEntityMapper;
import com.emazon.stockService.adapters.driven.jpa.mysql.repository.ArticleRepository;
import com.emazon.stockService.domain.model.Article;
import com.emazon.stockService.domain.spi.ArticlePersistencePort;

import java.util.Optional;

public class ArticleJpaAdapter implements ArticlePersistencePort {
    private final ArticleRepository articleRepository;
    private final ArticleEntityMapper articleEntityMapper;

    public ArticleJpaAdapter(ArticleRepository articleRepository, ArticleEntityMapper articleEntityMapper) {
        this.articleRepository = articleRepository;
        this.articleEntityMapper = articleEntityMapper;
    }

    @Override
    public Article saveArticle(Article article) {
        ArticleEntity articleEntity = articleEntityMapper.toEntity(article);
        ArticleEntity savedEntity = articleRepository.save(articleEntity);
        return articleEntityMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Article> findById(Long id) {
        return articleRepository.findById(id).map(articleEntityMapper::toDomain);
    }
}