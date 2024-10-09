package com.emazon.stockService.domain.spi;

import com.emazon.stockService.domain.model.Category;

import java.util.Optional;

public interface CategoryPersistencePort {
    Category saveCategory(Category category);
    Optional<Category> findById(Long id);
    boolean existsByName(String name);
}