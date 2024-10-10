package com.emazon.stockService.domain.useCase;

import com.emazon.stockService.domain.api.CategoryServicePort;
import com.emazon.stockService.domain.model.Category;
import com.emazon.stockService.domain.spi.CategoryPersistencePort;
import com.emazon.stockService.domain.exception.CategoryNotFoundException;
import com.emazon.stockService.domain.exception.InvalidCategoryNameException;
import com.emazon.stockService.domain.exception.InvalidCategoryDescriptionException;
import com.emazon.stockService.domain.exception.DuplicateCategoryNameException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

public class CategoryUseCase implements CategoryServicePort {
    private final CategoryPersistencePort categoryPersistencePort;

    public CategoryUseCase(CategoryPersistencePort categoryPersistencePort) {
        this.categoryPersistencePort = categoryPersistencePort;
    }

    @Override
    public Category createCategory(Category category) {
        validateCategory(category);
        return categoryPersistencePort.saveCategory(category);
    }

    @Override
    public Category getCategoryById(Long id) {
        return categoryPersistencePort.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Category not found with id: " + id));
    }

    @Override
    public boolean existsByName(String name) {
        return categoryPersistencePort.existsByName(name);
    }

    @Override
    public Page<Category> listCategories(Pageable pageable, String sortBy, String sortDirection) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        Pageable pageableWithSort = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);
        return categoryPersistencePort.findAll(pageableWithSort);
    }

    private void validateCategory(Category category) {
        if (category.getName() == null || category.getName().trim().isEmpty()) {
            throw new InvalidCategoryNameException("Category name cannot be empty");
        }
        if (category.getName().length() > 50) {
            throw new InvalidCategoryNameException("Category name cannot exceed 50 characters");
        }
        if (category.getDescription() == null || category.getDescription().trim().isEmpty()) {
            throw new InvalidCategoryDescriptionException("Category description cannot be empty");
        }
        if (category.getDescription().length() > 90) {
            throw new InvalidCategoryDescriptionException("Category description cannot exceed 90 characters");
        }
        if (categoryPersistencePort.existsByName(category.getName())) {
            throw new DuplicateCategoryNameException("Category name already exists");
        }
    }
}