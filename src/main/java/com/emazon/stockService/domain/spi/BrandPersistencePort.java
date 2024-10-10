package com.emazon.stockService.domain.spi;

import com.emazon.stockService.domain.model.Brand;
import  java.util.Optional;

public interface BrandPersistencePort {
    Brand saveBrand(Brand brand);
    Optional<Brand> findById(Long id);
    boolean existsByName(String name);
}
