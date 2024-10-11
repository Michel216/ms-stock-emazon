package com.emazon.stockService.adapters.driven.jpa.mysql.mapper;

import com.emazon.stockService.adapters.driven.jpa.mysql.entity.BrandEntity;
import com.emazon.stockService.domain.model.Brand;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-11T12:34:15-0500",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.8.jar, environment: Java 17.0.12 (Amazon.com Inc.)"
)
@Component
public class BrandEntityMapperImpl implements BrandEntityMapper {

    @Override
    public Brand toDomain(BrandEntity entity) {
        if ( entity == null ) {
            return null;
        }

        Brand brand = new Brand();

        brand.setId( entity.getId() );
        brand.setName( entity.getName() );
        brand.setDescription( entity.getDescription() );

        return brand;
    }

    @Override
    public BrandEntity toEntity(Brand domain) {
        if ( domain == null ) {
            return null;
        }

        BrandEntity brandEntity = new BrandEntity();

        brandEntity.setId( domain.getId() );
        brandEntity.setName( domain.getName() );
        brandEntity.setDescription( domain.getDescription() );

        return brandEntity;
    }
}
