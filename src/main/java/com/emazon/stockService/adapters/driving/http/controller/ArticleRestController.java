// src/main/java/com/emazon/stockService/adapters/driving/http/controller/ArticleRestController.java

package com.emazon.stockService.adapters.driving.http.controller;

import com.emazon.stockService.adapters.driving.http.dto.request.ArticleRequest;
import com.emazon.stockService.adapters.driving.http.dto.response.ArticleResponse;
import com.emazon.stockService.adapters.driving.http.mapper.request.ArticleRequestMapper;
import com.emazon.stockService.adapters.driving.http.mapper.response.ArticleResponseMapper;
import com.emazon.stockService.domain.api.ArticleServicePort;
import com.emazon.stockService.domain.model.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/articles")
public class ArticleRestController {
    private final ArticleServicePort articleServicePort;
    private final ArticleRequestMapper articleRequestMapper;
    private final ArticleResponseMapper articleResponseMapper;

    public ArticleRestController(ArticleServicePort articleServicePort,
                                 ArticleRequestMapper articleRequestMapper,
                                 ArticleResponseMapper articleResponseMapper) {
        this.articleServicePort = articleServicePort;
        this.articleRequestMapper = articleRequestMapper;
        this.articleResponseMapper = articleResponseMapper;
    }

    @PostMapping
    public ResponseEntity<ArticleResponse> createArticle(@Valid @RequestBody ArticleRequest articleRequest) {
        Article article = articleRequestMapper.toDomain(articleRequest);
        Article createdArticle = articleServicePort.createArticle(article);
        ArticleResponse response = articleResponseMapper.toResponse(createdArticle);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArticleResponse> getArticleById(@PathVariable Long id) {
        Article article = articleServicePort.getArticleById(id);
        ArticleResponse response = articleResponseMapper.toResponse(article);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Page<ArticleResponse>> listArticles(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Long categoryId,
            @RequestParam(required = false) Long brandId,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "ASC") String sortDirection,
            Pageable pageable) {
        Page<Article> articles = articleServicePort.listArticles(name, categoryId, brandId, sortBy, sortDirection, pageable);
        Page<ArticleResponse> response = articles.map(articleResponseMapper::toResponse);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ArticleResponse> updateArticle(@PathVariable Long id, @Valid @RequestBody ArticleRequest articleRequest) {
        Article article = articleRequestMapper.toDomain(articleRequest);
        Article updatedArticle = articleServicePort.updateArticle(id, article);
        ArticleResponse response = articleResponseMapper.toResponse(updatedArticle);
        return ResponseEntity.ok(response);
    }
}