package com.emazon.stockService.configuration;

import com.emazon.stockService.adapters.driven.jpa.mysql.adapter.CategoryJpaAdapter;
import com.emazon.stockService.adapters.driven.jpa.mysql.mapper.CategoryEntityMapper;
import com.emazon.stockService.adapters.driven.jpa.mysql.repository.CategoryRepository;
import com.emazon.stockService.domain.api.CategoryServicePort;
import com.emazon.stockService.domain.spi.CategoryPersistencePort;
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
}
