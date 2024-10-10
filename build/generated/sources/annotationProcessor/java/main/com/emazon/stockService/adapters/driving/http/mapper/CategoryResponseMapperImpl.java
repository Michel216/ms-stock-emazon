package com.emazon.stockService.adapters.driving.http.mapper;

import com.emazon.stockService.adapters.driving.http.dto.response.CategoryResponse;
import com.emazon.stockService.domain.model.Category;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-10T16:14:00-0500",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.8.jar, environment: Java 17.0.12 (Amazon.com Inc.)"
)
@Component
public class CategoryResponseMapperImpl implements CategoryResponseMapper {

    @Override
    public CategoryResponse toResponse(Category createdCategory) {
        if ( createdCategory == null ) {
            return null;
        }

        CategoryResponse categoryResponse = new CategoryResponse();

        categoryResponse.setId( createdCategory.getId() );
        categoryResponse.setName( createdCategory.getName() );
        categoryResponse.setDescription( createdCategory.getDescription() );

        return categoryResponse;
    }
}
