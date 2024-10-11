package com.emazon.stockService.domain.useCase;

import com.emazon.stockService.domain.api.ArticleServicePort;
import com.emazon.stockService.domain.model.Article;
import com.emazon.stockService.domain.spi.ArticlePersistencePort;
import com.emazon.stockService.domain.exception.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.HashSet;

public class ArticleUseCase implements ArticleServicePort {
    private final ArticlePersistencePort articlePersistencePort;

    public ArticleUseCase(ArticlePersistencePort articlePersistencePort) {
        this.articlePersistencePort = articlePersistencePort;
    }

    @Override
    public Article createArticle(Article article) {
        validateArticle(article);
        return articlePersistencePort.saveArticle(article);
    }

    @Override
    public Article getArticleById(Long id) {
        return articlePersistencePort.findById(id)
                .orElseThrow(() -> new NotFoundException("Article not found with id: " + id));
    }
    @Override
    public Page<Article> listArticles(String name, Long categoryId, Long brandId, String sortBy, String sortDirection, Pageable pageable) {
        return articlePersistencePort.findAll(name, categoryId, brandId, sortBy, sortDirection, pageable);
    }

    @Override
    public Article updateArticle(Long id, Article updatedArticle) {
        Article existingArticle = getArticleById(id);

        existingArticle.setName(updatedArticle.getName());
        existingArticle.setDescription(updatedArticle.getDescription());
        existingArticle.setQuantity(updatedArticle.getQuantity());
        existingArticle.setPrice(updatedArticle.getPrice());
        existingArticle.setBrand(updatedArticle.getBrand());
        existingArticle.setCategories(updatedArticle.getCategories());

        return articlePersistencePort.updateArticle(existingArticle);
    }

    private void validateArticle(Article article) {
        if (article == null) {
            throw new InvalidArticleException("Article cannot be null");
        }
        if (article.getName() == null || article.getName().trim().isEmpty()) {
            throw new InvalidNameException("Article name cannot be empty");
        }
        if (article.getDescription() == null || article.getDescription().trim().isEmpty()) {
            throw new InvalidDescriptionException("Article description cannot be empty");
        }
        if (article.getQuantity() < 0) {
            throw new InvalidArticleException("Article quantity cannot be negative");
        }
        if (article.getPrice() == null || article.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidArticleException("Article price must be greater than zero");
        }
        if (article.getCategories() == null || article.getCategories().isEmpty()) {
            throw new InvalidArticleException("Article must have at least one category");
        }
        if (article.getCategories().size() > 3) {
            throw new InvalidArticleException("Article cannot have more than 3 categories");
        }
        if (article.getCategories().size() != new HashSet<>(article.getCategories()).size()) {
            throw new InvalidArticleException("Article cannot have repeated categories");
        }
    }
}