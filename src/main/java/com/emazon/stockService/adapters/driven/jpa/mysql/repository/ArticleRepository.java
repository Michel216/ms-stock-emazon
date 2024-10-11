// src/main/java/com/emazon/stockService/adapters/driven/jpa/mysql/repository/ArticleRepository.java

package com.emazon.stockService.adapters.driven.jpa.mysql.repository;

import com.emazon.stockService.adapters.driven.jpa.mysql.entity.ArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<ArticleEntity, Long> {
}