package com.emazon.stockService.adapters.driven.jpa.mysql.mapper;

import com.emazon.stockService.adapters.driven.jpa.mysql.entity.ArticleEntity;
import com.emazon.stockService.adapters.driven.jpa.mysql.entity.BrandEntity;
import com.emazon.stockService.adapters.driven.jpa.mysql.entity.CategoryEntity;
import com.emazon.stockService.domain.model.Article;
import com.emazon.stockService.domain.model.Brand;
import com.emazon.stockService.domain.model.Category;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-11T12:34:15-0500",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.8.jar, environment: Java 17.0.12 (Amazon.com Inc.)"
)
@Component
public class CategoryEntityMapperImpl implements CategoryEntityMapper {

    @Override
    public Category toDomain(CategoryEntity entity) {
        if ( entity == null ) {
            return null;
        }

        Category category = new Category();

        category.setId( entity.getId() );
        category.setName( entity.getName() );
        category.setDescription( entity.getDescription() );
        category.setArticles( articleEntitySetToArticleSet( entity.getArticles() ) );

        return category;
    }

    @Override
    public CategoryEntity toEntity(Category domain) {
        if ( domain == null ) {
            return null;
        }

        CategoryEntity categoryEntity = new CategoryEntity();

        categoryEntity.setId( domain.getId() );
        categoryEntity.setName( domain.getName() );
        categoryEntity.setDescription( domain.getDescription() );
        categoryEntity.setArticles( articleSetToArticleEntitySet( domain.getArticles() ) );

        return categoryEntity;
    }

    protected Brand brandEntityToBrand(BrandEntity brandEntity) {
        if ( brandEntity == null ) {
            return null;
        }

        Brand brand = new Brand();

        brand.setId( brandEntity.getId() );
        brand.setName( brandEntity.getName() );
        brand.setDescription( brandEntity.getDescription() );

        return brand;
    }

    protected Set<Category> categoryEntitySetToCategorySet(Set<CategoryEntity> set) {
        if ( set == null ) {
            return null;
        }

        Set<Category> set1 = new LinkedHashSet<Category>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( CategoryEntity categoryEntity : set ) {
            set1.add( toDomain( categoryEntity ) );
        }

        return set1;
    }

    protected Article articleEntityToArticle(ArticleEntity articleEntity) {
        if ( articleEntity == null ) {
            return null;
        }

        Article article = new Article();

        article.setId( articleEntity.getId() );
        article.setName( articleEntity.getName() );
        article.setDescription( articleEntity.getDescription() );
        article.setQuantity( articleEntity.getQuantity() );
        article.setPrice( articleEntity.getPrice() );
        article.setBrand( brandEntityToBrand( articleEntity.getBrand() ) );
        article.setCategories( categoryEntitySetToCategorySet( articleEntity.getCategories() ) );

        return article;
    }

    protected Set<Article> articleEntitySetToArticleSet(Set<ArticleEntity> set) {
        if ( set == null ) {
            return null;
        }

        Set<Article> set1 = new LinkedHashSet<Article>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( ArticleEntity articleEntity : set ) {
            set1.add( articleEntityToArticle( articleEntity ) );
        }

        return set1;
    }

    protected BrandEntity brandToBrandEntity(Brand brand) {
        if ( brand == null ) {
            return null;
        }

        BrandEntity brandEntity = new BrandEntity();

        brandEntity.setId( brand.getId() );
        brandEntity.setName( brand.getName() );
        brandEntity.setDescription( brand.getDescription() );

        return brandEntity;
    }

    protected Set<CategoryEntity> categorySetToCategoryEntitySet(Set<Category> set) {
        if ( set == null ) {
            return null;
        }

        Set<CategoryEntity> set1 = new LinkedHashSet<CategoryEntity>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Category category : set ) {
            set1.add( toEntity( category ) );
        }

        return set1;
    }

    protected ArticleEntity articleToArticleEntity(Article article) {
        if ( article == null ) {
            return null;
        }

        ArticleEntity articleEntity = new ArticleEntity();

        articleEntity.setId( article.getId() );
        articleEntity.setName( article.getName() );
        articleEntity.setDescription( article.getDescription() );
        articleEntity.setQuantity( article.getQuantity() );
        articleEntity.setPrice( article.getPrice() );
        articleEntity.setBrand( brandToBrandEntity( article.getBrand() ) );
        articleEntity.setCategories( categorySetToCategoryEntitySet( article.getCategories() ) );

        return articleEntity;
    }

    protected Set<ArticleEntity> articleSetToArticleEntitySet(Set<Article> set) {
        if ( set == null ) {
            return null;
        }

        Set<ArticleEntity> set1 = new LinkedHashSet<ArticleEntity>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Article article : set ) {
            set1.add( articleToArticleEntity( article ) );
        }

        return set1;
    }
}
