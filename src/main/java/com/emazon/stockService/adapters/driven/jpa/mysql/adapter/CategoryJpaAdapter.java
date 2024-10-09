package com.emazon.stockService.adapters.driven.jpa.mysql.adapter;

import com.emazon.stockService.adapters.driven.jpa.mysql.entity.CategoryEntity;
import com.emazon.stockService.adapters.driven.jpa.mysql.mapper.CategoryEntityMapper;
import com.emazon.stockService.adapters.driven.jpa.mysql.repository.CategoryRepository;
import com.emazon.stockService.domain.model.Category;
import com.emazon.stockService.domain.spi.CategoryPersistencePort;

import java.util.Optional;

public class CategoryJpaAdapter implements CategoryPersistencePort {
    private final CategoryRepository categoryRepository;
    private final CategoryEntityMapper categoryEntityMapper;

    public CategoryJpaAdapter(CategoryRepository categoryRepository, CategoryEntityMapper categoryEntityMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryEntityMapper = categoryEntityMapper;
    }

    @Override
    public Category saveCategory(Category category) {
        CategoryEntity categoryEntity = categoryEntityMapper.toEntity(category);
        CategoryEntity savedEntity = categoryRepository.save(categoryEntity);
        return categoryEntityMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Category> findById(Long id) {
        return categoryRepository.findById(id).map(categoryEntityMapper::toDomain);
    }

    @Override
    public boolean existsByName(String name) {
        return categoryRepository.existsByName(name);
    }
}