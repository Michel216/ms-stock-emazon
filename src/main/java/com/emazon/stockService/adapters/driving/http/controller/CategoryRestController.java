package com.emazon.stockService.adapters.driving.http.controller;

import com.emazon.stockService.adapters.driving.http.dto.request.CategoryRequest;
import com.emazon.stockService.adapters.driving.http.dto.response.CategoryResponse;
import com.emazon.stockService.adapters.driving.http.mapper.CategoryRequestMapper;
import com.emazon.stockService.adapters.driving.http.mapper.CategoryResponseMapper;
import com.emazon.stockService.domain.api.CategoryServicePort;
import com.emazon.stockService.domain.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/categories")
public class CategoryRestController {
    private final CategoryServicePort categoryServicePort;
    private final CategoryRequestMapper categoryRequestMapper;
    private final CategoryResponseMapper categoryResponseMapper;

    public CategoryRestController(CategoryServicePort categoryServicePort,
                                  CategoryRequestMapper categoryRequestMapper,
                                  CategoryResponseMapper categoryResponseMapper) {
        this.categoryServicePort = categoryServicePort;
        this.categoryRequestMapper = categoryRequestMapper;
        this.categoryResponseMapper = categoryResponseMapper;
    }

    @PostMapping
    public ResponseEntity<CategoryResponse> createCategory(@Valid @RequestBody CategoryRequest categoryRequest) {
        Category category = categoryRequestMapper.toDomain(categoryRequest);
        Category createdCategory = categoryServicePort.createCategory(category);
        CategoryResponse response = categoryResponseMapper.toResponse(createdCategory);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> getCategoryById(@PathVariable Long id) {
        Category category = categoryServicePort.getCategoryById(id);
        CategoryResponse response = categoryResponseMapper.toResponse(category);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Page<CategoryResponse>> listCategories(
            Pageable pageable,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDirection) {
        Page<Category> categories = categoryServicePort.listCategories(pageable, sortBy, sortDirection);
        Page<CategoryResponse> responsePages = categories.map(categoryResponseMapper::toResponse);
        return ResponseEntity.ok(responsePages);
    }
}