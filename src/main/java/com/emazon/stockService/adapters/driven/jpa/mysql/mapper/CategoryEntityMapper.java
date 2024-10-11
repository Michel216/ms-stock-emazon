// src/main/java/com/emazon/stockService/adapters/driven/jpa/mysql/mapper/CategoryEntityMapper.java

package com.emazon.stockService.adapters.driven.jpa.mysql.mapper;

import com.emazon.stockService.adapters.driven.jpa.mysql.entity.CategoryEntity;
import com.emazon.stockService.domain.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CategoryEntityMapper {
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "articles", source = "articles")
    Category toDomain(CategoryEntity entity);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "description", source = "description")
    CategoryEntity toEntity(Category domain);
}