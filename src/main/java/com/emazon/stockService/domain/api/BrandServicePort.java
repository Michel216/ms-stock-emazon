package com.emazon.stockService.domain.api;

import com.emazon.stockService.domain.model.Brand;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BrandServicePort {
    Brand createBrand(Brand brand);
    Brand getBrandById(Long id);
    boolean existsByName(String name);
    Page<Brand> listBrands(Pageable pageable, String sortBy, String sortDirection);
}