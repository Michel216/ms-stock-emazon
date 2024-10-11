package com.emazon.stockService.adapters.driving.http.controller;

import com.emazon.stockService.adapters.driving.http.dto.request.ArticleRequest;
import com.emazon.stockService.domain.api.ArticleServicePort;
import com.emazon.stockService.domain.exception.NotFoundException;
import com.emazon.stockService.domain.model.Article;
import com.emazon.stockService.domain.model.Brand;
import com.emazon.stockService.domain.model.Category;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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

    @Test
    void getArticleById_NonExistingId_ShouldReturnNotFound() throws Exception {
        Long articleId = 1L;

        when(articleServicePort.getArticleById(articleId)).thenThrow(new NotFoundException("Article not found with id: " + articleId));

        mockMvc.perform(get("/api/articles/{id}", articleId))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.message").value("Article not found with id: " + articleId));
    }
    
    @Test
    void listArticles_ShouldReturnPageOfArticles() throws Exception {
        String name = "Test";
        Long categoryId = 1L;
        Long brandId = 1L;
        String sortBy = "name";
        String sortDirection = "ASC";
        Pageable pageable = PageRequest.of(0, 10);
        Article article1 = createValidArticle(1L);
        Article article2 = createValidArticle(2L);
        Page<Article> articlePage = new PageImpl<>(Arrays.asList(article1, article2));

        when(articleServicePort.listArticles(name, categoryId, brandId, sortBy, sortDirection, pageable)).thenReturn(articlePage);

        mockMvc.perform(get("/api/articles")
                        .param("name", name)
                        .param("categoryId", categoryId.toString())
                        .param("brandId", brandId.toString())
                        .param("sortBy", sortBy)
                        .param("sortDirection", sortDirection)
                        .param("page", "0")
                        .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.length()").value(2))
                .andExpect(jsonPath("$.content[0].id").value(1))
                .andExpect(jsonPath("$.content[1].id").value(2))
                .andExpect(jsonPath("$.totalElements").value(2))
                .andExpect(jsonPath("$.totalPages").value(1));
    }

    @Test
    void updateArticle_ExistingArticle_ShouldReturnUpdatedArticle() throws Exception {
        Long articleId = 1L;
        ArticleRequest request = createValidArticleRequest();
        request.setName("Updated Article");
        Article updatedArticle = createValidArticle(articleId);
        updatedArticle.setName("Updated Article");

        when(articleServicePort.updateArticle(any(Long.class), any(Article.class))).thenReturn(updatedArticle);

        mockMvc.perform(put("/api/articles/{id}", articleId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(articleId))
                .andExpect(jsonPath("$.name").value("Updated Article"));
    }

    private ArticleRequest createValidArticleRequest() {
        ArticleRequest request = new ArticleRequest();
        request.setName("Test Article");
        request.setDescription("Test Description");
        request.setQuantity(10);
        request.setPrice(new BigDecimal("19.99"));
        request.setBrandId(1L);
        Set<Long> categoryIds = new HashSet<>();
        categoryIds.add(1L);
        request.setCategoryIds(categoryIds);
        return request;
    }

    private Article createValidArticle(Long id) {
        Article article = new Article();
        article.setId(id);
        article.setName("Test Article");
        article.setDescription("Test Description");
        article.setQuantity(10);
        article.setPrice(new BigDecimal("19.99"));
        article.setBrand(new Brand(1L, "Test Brand", "Test Brand Description"));
        Set<Category> categories = new HashSet<>();
        categories.add(new Category(1L, "Category 1", "Description 1"));
        article.setCategories(categories);
        return article;
    }
}