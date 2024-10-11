package com.emazon.stockService.adapters.driving.http.mapper.request;

import com.emazon.stockService.adapters.driving.http.dto.request.BrandRequest;
import com.emazon.stockService.domain.model.Brand;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-11T12:34:15-0500",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.8.jar, environment: Java 17.0.12 (Amazon.com Inc.)"
)
@Component
public class BrandRequestMapperImpl implements BrandRequestMapper {

    @Override
    public Brand toDomain(BrandRequest request) {
        if ( request == null ) {
            return null;
        }

        Brand brand = new Brand();

        brand.setName( request.getName() );
        brand.setDescription( request.getDescription() );

        return brand;
    }
}
