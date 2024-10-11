// src/test/java/com/emazon/stockService/adapters/driving/http/controller/ArticleRestControllerIntegrationTest.java

package com.emazon.stockService.adapters.driving.http.controller;

import com.emazon.stockService.adapters.driving.http.dto.request.ArticleRequest;
import com.emazon.stockService.domain.api.ArticleServicePort;
import com.emazon.stockService.domain.model.Article;
import com.emazon.stockService.domain.model.Brand;
import com.emazon.stockService.domain.model.Category;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ArticleRestController.class)
class ArticleRestControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ArticleServicePort articleServicePort;

    @Test
    void createArticle_ValidRequest_ShouldReturnCreatedArticle() throws Exception {
        ArticleRequest request = new ArticleRequest();
        request.setName("Test Article");
        request.setDescription("Test Description");
        request.setQuantity(10);
        request.setPrice(new BigDecimal("19.99"));
        request.setBrandId(1L);
        Set<Long> categoryIds = new HashSet<>();
        categoryIds.add(1L);
        request.setCategoryIds(categoryIds);

        Article createdArticle = new Article(1L, "Test Article", "Test Description", 10, new BigDecimal("19.99"),
                new Brand(1L, "Test Brand", "Test Brand Description"));
        Set<Category> categories = new HashSet<>();
        categories.add(new Category(1L, "Category 1", "Description 1"));
        createdArticle.setCategories(categories);

        when(articleServicePort.createArticle(any(Article.class))).thenReturn(createdArticle);

        mockMvc.perform(post("/api/articles")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Test Article"))
                .andExpect(jsonPath("$.description").value("Test Description"))
                .andExpect(jsonPath("$.quantity").value(10))
                .andExpect(jsonPath("$.price").value(19.99))
                .andExpect(jsonPath("$.brand.id").value(1L))
                .andExpect(jsonPath("$.categories[0].id").value(1L));
    }

    @Test
    void getArticleById_ExistingId_ShouldReturnArticle() throws Exception {
        Long articleId = 1L;
        Article article = new Article(articleId, "Test Article", "Test Description", 10, new BigDecimal("19.99"),
                new Brand(1L, "Test Brand", "Test Brand Description"));
        Set<Category> categories = new HashSet<>();
        categories.add(new Category(1L, "Category 1", "Description 1"));
        article.setCategories(categories);

        when(articleServicePort.getArticleById(articleId)).thenReturn(article);

        mockMvc.perform(get("/api/articles/{id}", articleId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(articleId))
                .andExpect(jsonPath("$.name").value("Test Article"))
                .andExpect(jsonPath("$.description").value("Test Description"))
                .andExpect(jsonPath("$.quantity").value(10))
                .andExpect(jsonPath("$.price").value(19.99))
                .andExpect(jsonPath("$.brand.id").value(1L))
                .andExpect(jsonPath("$.categories[0].id").value(1L));
    }
}