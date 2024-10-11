package com.emazon.stockService.adapters.driving.http.controller;

import com.emazon.stockService.adapters.driving.http.dto.request.ArticleRequest;
import com.emazon.stockService.adapters.driving.http.dto.response.ArticleResponse;
import com.emazon.stockService.adapters.driving.http.mapper.request.ArticleRequestMapper;
import com.emazon.stockService.adapters.driving.http.mapper.response.ArticleResponseMapper;
import com.emazon.stockService.domain.api.ArticleServicePort;
import com.emazon.stockService.domain.model.Article;
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
}