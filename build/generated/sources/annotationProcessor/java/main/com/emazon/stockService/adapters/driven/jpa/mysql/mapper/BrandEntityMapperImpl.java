package com.emazon.stockService.adapters.driven.jpa.mysql.mapper;

import com.emazon.stockService.adapters.driven.jpa.mysql.entity.BrandEntity;
import com.emazon.stockService.domain.model.Brand;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-10T16:14:00-0500",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.8.jar, environment: Java 17.0.12 (Amazon.com Inc.)"
)
@Component
public class BrandEntityMapperImpl implements BrandEntityMapper {

    @Override
    public Brand toDomain(BrandEntity entity) {
        if ( entity == null ) {
            return null;
        }

        Long id = null;
        String name = null;
        String description = null;

        id = entity.getId();
        name = entity.getName();
        description = entity.getDescription();

        Brand brand = new Brand( id, name, description );

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
