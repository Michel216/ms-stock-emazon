package com.emazon.stockService.domain.api;

import com.emazon.stockService.domain.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoryServicePort {
    Category createCategory(Category category);
    Category getCategoryById(Long id);
    boolean existsByName(String name);
    Page<Category> listCategories(Pageable pageable, String sortBy, String sortDirection);
}