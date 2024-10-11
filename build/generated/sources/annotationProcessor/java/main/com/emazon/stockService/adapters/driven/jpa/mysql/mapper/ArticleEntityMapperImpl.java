package com.emazon.stockService.adapters.driven.jpa.mysql.mapper;

import com.emazon.stockService.adapters.driven.jpa.mysql.entity.ArticleEntity;
import com.emazon.stockService.adapters.driven.jpa.mysql.entity.CategoryEntity;
import com.emazon.stockService.domain.model.Article;
import com.emazon.stockService.domain.model.Category;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-11T12:34:15-0500",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.8.jar, environment: Java 17.0.12 (Amazon.com Inc.)"
)
@Component
public class ArticleEntityMapperImpl implements ArticleEntityMapper {

    @Autowired
    private BrandEntityMapper brandEntityMapper;
    @Autowired
    private CategoryEntityMapper categoryEntityMapper;

    @Override
    public Article toDomain(ArticleEntity entity) {
        if ( entity == null ) {
            return null;
        }

        Article article = new Article();

        article.setId( entity.getId() );
        article.setName( entity.getName() );
        article.setDescription( entity.getDescription() );
        article.setQuantity( entity.getQuantity() );
        article.setPrice( entity.getPrice() );
        article.setBrand( brandEntityMapper.toDomain( entity.getBrand() ) );
        article.setCategories( categoryEntitySetToCategorySet( entity.getCategories() ) );

        return article;
    }

    @Override
    public ArticleEntity toEntity(Article domain) {
        if ( domain == null ) {
            return null;
        }

        ArticleEntity articleEntity = new ArticleEntity();

        articleEntity.setId( domain.getId() );
        articleEntity.setName( domain.getName() );
        articleEntity.setDescription( domain.getDescription() );
        articleEntity.setQuantity( domain.getQuantity() );
        articleEntity.setPrice( domain.getPrice() );
        articleEntity.setBrand( brandEntityMapper.toEntity( domain.getBrand() ) );
        articleEntity.setCategories( categorySetToCategoryEntitySet( domain.getCategories() ) );

        return articleEntity;
    }

    protected Set<Category> categoryEntitySetToCategorySet(Set<CategoryEntity> set) {
        if ( set == null ) {
            return null;
        }

        Set<Category> set1 = new LinkedHashSet<Category>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( CategoryEntity categoryEntity : set ) {
            set1.add( categoryEntityMapper.toDomain( categoryEntity ) );
        }

        return set1;
    }

    protected Set<CategoryEntity> categorySetToCategoryEntitySet(Set<Category> set) {
        if ( set == null ) {
            return null;
        }

        Set<CategoryEntity> set1 = new LinkedHashSet<CategoryEntity>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Category category : set ) {
            set1.add( categoryEntityMapper.toEntity( category ) );
        }

        return set1;
    }
}
