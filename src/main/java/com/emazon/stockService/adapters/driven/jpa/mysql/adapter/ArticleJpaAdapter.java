package com.emazon.stockService.adapters.driven.jpa.mysql.adapter;

import com.emazon.stockService.adapters.driven.jpa.mysql.entity.ArticleEntity;
import com.emazon.stockService.adapters.driven.jpa.mysql.mapper.ArticleEntityMapper;
import com.emazon.stockService.adapters.driven.jpa.mysql.repository.ArticleRepository;
import com.emazon.stockService.domain.model.Article;
import com.emazon.stockService.domain.spi.ArticlePersistencePort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

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

    @Override
    public Page<Article> findAll(String name, Long categoryId, Long brandId, String sortBy, String sortDirection, Pageable pageable) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        Pageable pageableWithSort = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);

        Page<ArticleEntity> articleEntities = articleRepository.findAllByNameContainingAndCategoryIdAndBrandId(name, categoryId, brandId, pageableWithSort);
        return articleEntities.map(articleEntityMapper::toDomain);
    }

    @Override
    public Article updateArticle(Article article) {
        ArticleEntity articleEntity = articleEntityMapper.toEntity(article);
        ArticleEntity updatedEntity = articleRepository.save(articleEntity);
        return articleEntityMapper.toDomain(updatedEntity);
    }
}