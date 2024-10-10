package com.emazon.stockService.adapters.driven.jpa.mysql.adapter;

import com.emazon.stockService.adapters.driven.jpa.mysql.entity.BrandEntity;
import com.emazon.stockService.adapters.driven.jpa.mysql.mapper.BrandEntityMapper;
import com.emazon.stockService.adapters.driven.jpa.mysql.repository.BrandRepository;
import com.emazon.stockService.domain.model.Brand;
import com.emazon.stockService.domain.spi.BrandPersistencePort;

import java.util.Optional;

public class BrandJpaAdapter implements BrandPersistencePort {
    private final BrandRepository brandRepository;
    private final BrandEntityMapper brandEntityMapper;

    public BrandJpaAdapter(BrandRepository brandRepository, BrandEntityMapper brandEntityMapper) {
        this.brandRepository = brandRepository;
        this.brandEntityMapper = brandEntityMapper;
    }

    @Override
    public Brand saveBrand(Brand brand) {
        BrandEntity brandEntity = brandEntityMapper.toEntity(brand);
        BrandEntity savedEntity = brandRepository.save(brandEntity);
        return brandEntityMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Brand> findById(Long id) {
        return brandRepository.findById(id).map(brandEntityMapper::toDomain);
    }

    @Override
    public boolean existsByName(String name) {
        return brandRepository.existsByName(name);
    }
}