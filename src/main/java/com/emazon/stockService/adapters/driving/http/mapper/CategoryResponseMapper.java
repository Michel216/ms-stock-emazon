package com.emazon.stockService.adapters.driving.http.mapper;

import com.emazon.stockService.adapters.driving.http.dto.response.CategoryResponse;
import com.emazon.stockService.domain.model.Category;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface CategoryResponseMapper {
    CategoryResponse toResponse(Category createdCategory);
}