package com.emazon.stockService.domain.api;

import com.emazon.stockService.domain.model.Category;

public interface CategoryServicePort {
    Category createCategory(Category category);
    Category getCategoryById(Long id);
    boolean existsByName(String name);
}