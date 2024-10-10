package com.emazon.stockService.adapters.driving.http.mapper;

import com.emazon.stockService.adapters.driving.http.dto.request.BrandRequest;
import com.emazon.stockService.domain.model.Brand;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BrandRequestMapper {
    @Mapping(target = "id", ignore = true)
    Brand toDomain(BrandRequest request);
}