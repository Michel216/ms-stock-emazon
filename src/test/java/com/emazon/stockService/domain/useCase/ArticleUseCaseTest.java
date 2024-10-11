package com.emazon.stockService.domain.useCase;

import com.emazon.stockService.domain.exception.NotFoundException;
import com.emazon.stockService.domain.exception.InvalidArticleException;
import com.emazon.stockService.domain.model.Article;
import com.emazon.stockService.domain.model.Brand;
import com.emazon.stockService.domain.model.Category;
import com.emazon.stockService.domain.spi.ArticlePersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ArticleUseCaseTest {

    @Mock
    private ArticlePersistencePort articlePersistencePort;

    private ArticleUseCase articleUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        articleUseCase = new ArticleUseCase(articlePersistencePort);
    }

    @Test
    void createArticle_ValidArticle_ShouldCreateSuccessfully() {
        Article article = createValidArticle();
        when(articlePersistencePort.saveArticle(any(Article.class))).thenReturn(article);

        Article result = articleUseCase.createArticle(article);

        assertNotNull(result);
        assertEquals(article.getName(), result.getName());
        assertEquals(article.getDescription(), result.getDescription());
        assertEquals(article.getQuantity(), result.getQuantity());
        assertEquals(article.getPrice(), result.getPrice());
        assertEquals(article.getBrand(), result.getBrand());
        assertEquals(article.getCategories(), result.getCategories());
        verify(articlePersistencePort).saveArticle(article);
    }

    @Test
    void createArticle_EmptyName_ShouldThrowInvalidArticleException() {
        Article article = createValidArticle();
        article.setName("");

        assertThrows(InvalidArticleException.class, () -> articleUseCase.createArticle(article));
    }

    @Test
    void createArticle_EmptyDescription_ShouldThrowInvalidArticleException() {
        Article article = createValidArticle();
        article.setDescription("");

        assertThrows(InvalidArticleException.class, () -> articleUseCase.createArticle(article));
    }

    @Test
    void createArticle_NegativeQuantity_ShouldThrowInvalidArticleException() {
        Article article = createValidArticle();
        article.setQuantity(-1);

        assertThrows(InvalidArticleException.class, () -> articleUseCase.createArticle(article));
    }

    @Test
    void createArticle_ZeroPrice_ShouldThrowInvalidArticleException() {
        Article article = createValidArticle();
        article.setPrice(BigDecimal.ZERO);

        assertThrows(InvalidArticleException.class, () -> articleUseCase.createArticle(article));
    }

    @Test
    void createArticle_NoBrand_ShouldThrowInvalidArticleException() {
        Article article = createValidArticle();
        article.setBrand(null);

        assertThrows(InvalidArticleException.class, () -> articleUseCase.createArticle(article));
    }

    @Test
    void createArticle_NoCategories_ShouldThrowInvalidArticleException() {
        Article article = createValidArticle();
        article.setCategories(new HashSet<>());

        assertThrows(InvalidArticleException.class, () -> articleUseCase.createArticle(article));
    }

    @Test
    void createArticle_TooManyCategories_ShouldThrowInvalidArticleException() {
        Article article = createValidArticle();
        Set<Category> categories = new HashSet<>();
        for (int i = 0; i < 4; i++) {
            categories.add(new Category((long) i, "Category " + i, "Description " + i));
        }
        article.setCategories(categories);

        assertThrows(InvalidArticleException.class, () -> articleUseCase.createArticle(article));
    }

    @Test
    void getArticleById_ExistingId_ShouldReturnArticle() {
        Long id = 1L;
        Article article = createValidArticle();
        when(articlePersistencePort.findById(id)).thenReturn(Optional.of(article));

        Article result = articleUseCase.getArticleById(id);

        assertNotNull(result);
        assertEquals(article.getId(), result.getId());
        assertEquals(article.getName(), result.getName());
        assertEquals(article.getDescription(), result.getDescription());
        assertEquals(article.getQuantity(), result.getQuantity());
        assertEquals(article.getPrice(), result.getPrice());
        assertEquals(article.getBrand(), result.getBrand());
        assertEquals(article.getCategories(), result.getCategories());
        verify(articlePersistencePort).findById(id);
    }

    @Test
    void getArticleById_NonExistingId_ShouldThrowArticleNotFoundException() {
        Long id = 1L;
        when(articlePersistencePort.findById(id)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> articleUseCase.getArticleById(id));
        verify(articlePersistencePort).findById(id);
    }


    private Article createValidArticle() {
        Article article = new Article();
        article.setId(1L);
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