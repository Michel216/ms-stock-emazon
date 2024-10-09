package com.emazon.stockService.adapters.driven.jpa.mysql.mapper;

import com.emazon.stockService.adapters.driven.jpa.mysql.entity.CategoryEntity;
import com.emazon.stockService.domain.model.Category;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-09T15:04:51-0500",
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

        return categoryEntity;
    }
}
