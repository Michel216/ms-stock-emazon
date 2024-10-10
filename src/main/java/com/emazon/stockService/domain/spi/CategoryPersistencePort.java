package com.emazon.stockService.domain.spi;

import com.emazon.stockService.domain.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface CategoryPersistencePort {
    Category saveCategory(Category category);
    Optional<Category> findById(Long id);
    boolean existsByName(String name);
    Page<Category> findAll(Pageable pageable);
}