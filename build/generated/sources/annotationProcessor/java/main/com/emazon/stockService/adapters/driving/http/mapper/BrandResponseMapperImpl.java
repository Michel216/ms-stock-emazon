package com.emazon.stockService.adapters.driving.http.mapper;

import com.emazon.stockService.adapters.driving.http.dto.response.BrandResponse;
import com.emazon.stockService.domain.model.Brand;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-10T16:14:01-0500",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.8.jar, environment: Java 17.0.12 (Amazon.com Inc.)"
)
@Component
public class BrandResponseMapperImpl implements BrandResponseMapper {

    @Override
    public BrandResponse toResponse(Brand brand) {
        if ( brand == null ) {
            return null;
        }

        BrandResponse brandResponse = new BrandResponse();

        brandResponse.setId( brand.getId() );
        brandResponse.setName( brand.getName() );
        brandResponse.setDescription( brand.getDescription() );

        return brandResponse;
    }
}
