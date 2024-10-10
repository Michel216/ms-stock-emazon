package com.emazon.stockService.domain.useCase;

import com.emazon.stockService.domain.api.BrandServicePort;
import com.emazon.stockService.domain.model.Brand;
import com.emazon.stockService.domain.spi.BrandPersistencePort;
import com.emazon.stockService.domain.exception.NotFoundException;
import com.emazon.stockService.domain.exception.InvalidNameException;
import com.emazon.stockService.domain.exception.InvalidDescriptionException;
import com.emazon.stockService.domain.exception.DuplicateNameException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.PageRequest;

public class BrandUseCase implements BrandServicePort {
    private final BrandPersistencePort brandPersistencePort;

    public BrandUseCase(BrandPersistencePort brandPersistencePort) {
        this.brandPersistencePort = brandPersistencePort;
    }

    @Override
    public Brand createBrand(Brand brand) {
        validateBrand(brand);
        return brandPersistencePort.saveBrand(brand);
    }

    @Override
    public Brand getBrandById(Long id) {
        return brandPersistencePort.findById(id)
                .orElseThrow(() -> new NotFoundException("Brand not found with id: " + id));
    }

    @Override
    public boolean existsByName(String name) {
        return brandPersistencePort.existsByName(name);
    }

    @Override
    public Page<Brand> listBrands(Pageable pageable, String sortBy, String sortDirection) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        Pageable pageableWithSort = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);
        return brandPersistencePort.findAll(pageableWithSort);
    }

    private void validateBrand(Brand brand) {
        if (brand == null) {
            throw new InvalidNameException("Brand cannot be null");
        }

        if (brand.getName() == null || brand.getName().trim().isEmpty()) {
            throw new InvalidNameException("Brand name cannot be empty");
        }
        if (brand.getName().length() > 50) {
            throw new InvalidNameException("Brand name cannot exceed 50 characters");
        }
        if (brand.getDescription().trim().isEmpty()) {
            throw new InvalidDescriptionException("Brand description cannot be empty");
        }
        if (brand.getDescription().length() > 120) {
            throw new InvalidDescriptionException("Brand description cannot exceed 120 characters");
        }
        if (brandPersistencePort.existsByName(brand.getName())) {
            throw new DuplicateNameException("Brand name already exists");
        }
    }
}
