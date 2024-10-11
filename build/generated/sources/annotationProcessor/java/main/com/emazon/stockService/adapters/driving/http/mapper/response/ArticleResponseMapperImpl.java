package com.emazon.stockService.adapters.driving.http.mapper.response;

import com.emazon.stockService.adapters.driving.http.dto.response.ArticleResponse;
import com.emazon.stockService.adapters.driving.http.dto.response.CategoryResponse;
import com.emazon.stockService.domain.model.Article;
import com.emazon.stockService.domain.model.Category;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-11T15:09:29-0500",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.8.jar, environment: Java 17.0.12 (Amazon.com Inc.)"
)
@Component
public class ArticleResponseMapperImpl implements ArticleResponseMapper {

    @Autowired
    private BrandResponseMapper brandResponseMapper;
    @Autowired
    private CategoryResponseMapper categoryResponseMapper;

    @Override
    public ArticleResponse toResponse(Article article) {
        if ( article == null ) {
            return null;
        }

        ArticleResponse articleResponse = new ArticleResponse();

        articleResponse.setId( article.getId() );
        articleResponse.setName( article.getName() );
        articleResponse.setDescription( article.getDescription() );
        articleResponse.setQuantity( article.getQuantity() );
        articleResponse.setPrice( article.getPrice() );
        articleResponse.setBrand( brandResponseMapper.toResponse( article.getBrand() ) );
        articleResponse.setCategories( categorySetToCategoryResponseSet( article.getCategories() ) );

        return articleResponse;
    }

    protected Set<CategoryResponse> categorySetToCategoryResponseSet(Set<Category> set) {
        if ( set == null ) {
            return null;
        }

        Set<CategoryResponse> set1 = new LinkedHashSet<CategoryResponse>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Category category : set ) {
            set1.add( categoryResponseMapper.toResponse( category ) );
        }

        return set1;
    }
}
