package com.emazon.stockService.domain.api;

import com.emazon.stockService.domain.model.Brand;

public interface BrandServicePort {
    Brand createBrand(Brand brand);
    Brand getBrandById(Long id);
    boolean existsByName(String name);
}
