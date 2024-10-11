package com.emazon.stockService.adapters.driving.http.mapper.request;

import com.emazon.stockService.adapters.driving.http.dto.request.ArticleRequest;
import com.emazon.stockService.domain.model.Article;
import com.emazon.stockService.domain.model.Brand;
import com.emazon.stockService.domain.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface ArticleRequestMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "brand", expression = "java(mapBrand(articleRequest.getBrandId()))")
    @Mapping(target = "categories", expression = "java(mapCategories(articleRequest.getCategoryIds()))")
    Article toDomain(ArticleRequest articleRequest);

    default Brand mapBrand(Long brandId) {
        if (brandId == null) {
            return null;
        }
        Brand brand = new Brand();
        brand.setId(brandId);
        return brand;
    }

    default Set<Category> mapCategories(Set<Long> categoryIds) {
        if (categoryIds == null) {
            return null;
        }
        return categoryIds.stream()
                .map(id -> {
                    Category category = new Category();
                    category.setId(id);
                    return category;
                })
                .collect(Collectors.toSet());
    }
}