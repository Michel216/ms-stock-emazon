package com.emazon.stockService.configuration;

import com.emazon.stockService.adapters.driven.jpa.mysql.adapter.BrandJpaAdapter;
import com.emazon.stockService.adapters.driven.jpa.mysql.adapter.CategoryJpaAdapter;
import com.emazon.stockService.adapters.driven.jpa.mysql.mapper.BrandEntityMapper;
import com.emazon.stockService.adapters.driven.jpa.mysql.mapper.CategoryEntityMapper;
import com.emazon.stockService.adapters.driven.jpa.mysql.repository.BrandRepository;
import com.emazon.stockService.adapters.driven.jpa.mysql.repository.CategoryRepository;
import com.emazon.stockService.domain.api.BrandServicePort;
import com.emazon.stockService.domain.api.CategoryServicePort;
import com.emazon.stockService.domain.spi.BrandPersistencePort;
import com.emazon.stockService.domain.spi.CategoryPersistencePort;
import com.emazon.stockService.domain.useCase.BrandUseCase;
import com.emazon.stockService.domain.useCase.CategoryUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    private final CategoryRepository categoryRepository;
    private final CategoryEntityMapper categoryEntityMapper;

    @Bean
    public CategoryPersistencePort categoryPersistencePort() {
        return new CategoryJpaAdapter(categoryRepository, categoryEntityMapper);
    }

    @Bean
    public CategoryServicePort categoryServicePort(CategoryPersistencePort categoryPersistencePort) {
        return new CategoryUseCase(categoryPersistencePort);
    }

    @Bean
    public BrandPersistencePort brandPersistencePort(BrandRepository brandRepository, BrandEntityMapper brandEntityMapper) {
        return new BrandJpaAdapter(brandRepository, brandEntityMapper);
    }

    @Bean
    public BrandServicePort brandServicePort(BrandPersistencePort brandPersistencePort) {
        return new BrandUseCase(brandPersistencePort);
    }
}
