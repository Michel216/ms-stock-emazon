package com.emazon.stockService.adapters.driving.http.mapper.request;

import com.emazon.stockService.adapters.driving.http.dto.request.ArticleRequest;
import com.emazon.stockService.domain.model.Article;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-11T12:34:15-0500",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.8.jar, environment: Java 17.0.12 (Amazon.com Inc.)"
)
@Component
public class ArticleRequestMapperImpl implements ArticleRequestMapper {

    @Override
    public Article toDomain(ArticleRequest articleRequest) {
        if ( articleRequest == null ) {
            return null;
        }

        Article article = new Article();

        article.setName( articleRequest.getName() );
        article.setDescription( articleRequest.getDescription() );
        if ( articleRequest.getQuantity() != null ) {
            article.setQuantity( articleRequest.getQuantity() );
        }
        article.setPrice( articleRequest.getPrice() );

        article.setBrand( mapBrand(articleRequest.getBrandId()) );
        article.setCategories( mapCategories(articleRequest.getCategoryIds()) );

        return article;
    }
}
