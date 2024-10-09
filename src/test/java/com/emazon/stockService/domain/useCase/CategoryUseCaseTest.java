package com.emazon.stockService.domain.useCase;

import com.emazon.stockService.domain.exception.CategoryNotFoundException;
import com.emazon.stockService.domain.exception.DuplicateCategoryNameException;
import com.emazon.stockService.domain.exception.InvalidCategoryDescriptionException;
import com.emazon.stockService.domain.exception.InvalidCategoryNameException;
import com.emazon.stockService.domain.model.Category;
import com.emazon.stockService.domain.spi.CategoryPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CategoryUseCaseTest {

    @Mock
    private CategoryPersistencePort categoryPersistencePort;

    private CategoryUseCase categoryUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        categoryUseCase = new CategoryUseCase(categoryPersistencePort);
    }

    @Test
    void createCategory_ValidCategory_ShouldCreateSuccessfully() {
        Category category = new Category(null, "Test Category", "Test Description");
        when(categoryPersistencePort.existsByName(anyString())).thenReturn(false);
        when(categoryPersistencePort.saveCategory(any(Category.class))).thenReturn(category);

        Category result = categoryUseCase.createCategory(category);

        assertNotNull(result);
        assertEquals(category.getName(), result.getName());
        assertEquals(category.getDescription(), result.getDescription());
        verify(categoryPersistencePort).saveCategory(category);
    }

    @Test
    void createCategory_EmptyName_ShouldThrowInvalidCategoryNameException() {
        Category category = new Category(null, "", "Test Description");

        assertThrows(InvalidCategoryNameException.class, () -> categoryUseCase.createCategory(category));
    }

    @Test
    void createCategory_LongName_ShouldThrowInvalidCategoryNameException() {
        Category category = new Category(null, "A".repeat(51), "Test Description");

        assertThrows(InvalidCategoryNameException.class, () -> categoryUseCase.createCategory(category));
    }

    @Test
    void createCategory_EmptyDescription_ShouldThrowInvalidCategoryDescriptionException() {
        Category category = new Category(null, "Test Category", "");

        assertThrows(InvalidCategoryDescriptionException.class, () -> categoryUseCase.createCategory(category));
    }

    @Test
    void createCategory_LongDescription_ShouldThrowInvalidCategoryDescriptionException() {
        Category category = new Category(null, "Test Category", "A".repeat(91));

        assertThrows(InvalidCategoryDescriptionException.class, () -> categoryUseCase.createCategory(category));
    }

   @Test
    void createCategory_DuplicateName_ShouldThrowDuplicateCategoryNameException() {
        Category category = new Category(null, "Test Category", "Test Description");
        when(categoryPersistencePort.existsByName(anyString())).thenReturn(true);

        assertThrows(DuplicateCategoryNameException.class, () -> categoryUseCase.createCategory(category));
    }

    @Test
    void getCategoryById_ExistingId_ShouldReturnCategory() {
        Long id = 1L;
        Category category = new Category(id, "Test Category", "Test Description");
        when(categoryPersistencePort.findById(id)).thenReturn(Optional.of(category));

        Category result = categoryUseCase.getCategoryById(id);

        assertNotNull(result);
        assertEquals(id, result.getId());
        assertEquals(category.getName(), result.getName());
        assertEquals(category.getDescription(), result.getDescription());
    }

    @Test
    void getCategoryById_NonExistingId_ShouldThrowCategoryNotFoundException() {
        Long id = 1L;
        when(categoryPersistencePort.findById(id)).thenReturn(Optional.empty());

        assertThrows(CategoryNotFoundException.class, () -> categoryUseCase.getCategoryById(id));
    }
}