// src/main/java/com/emazon/stockService/adapters/driving/http/mapper/ArticleResponseMapper.java

package com.emazon.stockService.adapters.driving.http.mapper.response;

import com.emazon.stockService.adapters.driving.http.dto.response.ArticleResponse;
import com.emazon.stockService.domain.model.Article;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {BrandResponseMapper.class, CategoryResponseMapper.class})
public interface ArticleResponseMapper {
    ArticleResponse toResponse(Article article);
}