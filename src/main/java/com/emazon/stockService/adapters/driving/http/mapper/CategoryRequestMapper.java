package com.emazon.stockService.adapters.driving.http.mapper;

import com.emazon.stockService.adapters.driving.http.dto.request.CategoryRequest;
import com.emazon.stockService.domain.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CategoryRequestMapper {
    @Mapping(target = "id", ignore = true)
    Category toDomain(CategoryRequest request);
}
