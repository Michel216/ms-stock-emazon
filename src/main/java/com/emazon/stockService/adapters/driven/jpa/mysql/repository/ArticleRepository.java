package com.emazon.stockService.adapters.driven.jpa.mysql.repository;

import com.emazon.stockService.adapters.driven.jpa.mysql.entity.ArticleEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<ArticleEntity, Long> {
    @Query("SELECT a FROM ArticleEntity a WHERE " +
            "(:name IS NULL OR a.name LIKE %:name%) AND " +
            "(:categoryId IS NULL OR :categoryId IN (SELECT c.id FROM a.categories c)) AND " +
            "(:brandId IS NULL OR a.brand.id = :brandId)")
    Page<ArticleEntity> findAllByNameContainingAndCategoryIdAndBrandId(
            @Param("name") String name,
            @Param("categoryId") Long categoryId,
            @Param("brandId") Long brandId,
            Pageable pageable);
}