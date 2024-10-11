// src/main/java/com/emazon/stockService/adapters/driven/jpa/mysql/mapper/ArticleEntityMapper.java

package com.emazon.stockService.adapters.driven.jpa.mysql.mapper;

import com.emazon.stockService.adapters.driven.jpa.mysql.entity.ArticleEntity;
import com.emazon.stockService.domain.model.Article;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {BrandEntityMapper.class, CategoryEntityMapper.class})
public interface ArticleEntityMapper {
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "quantity", source = "quantity")
    @Mapping(target = "price", source = "price")
    @Mapping(target = "brand", source = "brand")
    @Mapping(target = "categories", source = "categories")
    Article toDomain(ArticleEntity entity);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "quantity", source = "quantity")
    @Mapping(target = "price", source = "price")
    @Mapping(target = "brand", source = "brand")
    @Mapping(target = "categories", source = "categories")
    ArticleEntity toEntity(Article domain);
}