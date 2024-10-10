package com.emazon.stockService.adapters.driven.jpa.mysql.repository;

import com.emazon.stockService.adapters.driven.jpa.mysql.entity.BrandEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends JpaRepository<BrandEntity, Long> {
    boolean existsByName(String name);
}