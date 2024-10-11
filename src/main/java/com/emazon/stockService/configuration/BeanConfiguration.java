package com.emazon.stockService.configuration;

import com.emazon.stockService.adapters.driven.jpa.mysql.adapter.ArticleJpaAdapter;
import com.emazon.stockService.adapters.driven.jpa.mysql.adapter.BrandJpaAdapter;
import com.emazon.stockService.adapters.driven.jpa.mysql.adapter.CategoryJpaAdapter;
import com.emazon.stockService.adapters.driven.jpa.mysql.mapper.ArticleEntityMapper;
import com.emazon.stockService.adapters.driven.jpa.mysql.mapper.BrandEntityMapper;
import com.emazon.stockService.adapters.driven.jpa.mysql.mapper.CategoryEntityMapper;
import com.emazon.stockService.adapters.driven.jpa.mysql.repository.ArticleRepository;
import com.emazon.stockService.adapters.driven.jpa.mysql.repository.BrandRepository;
import com.emazon.stockService.adapters.driven.jpa.mysql.repository.CategoryRepository;
import com.emazon.stockService.domain.api.ArticleServicePort;
import com.emazon.stockService.domain.api.BrandServicePort;
import com.emazon.stockService.domain.api.CategoryServicePort;
import com.emazon.stockService.domain.spi.ArticlePersistencePort;
import com.emazon.stockService.domain.spi.BrandPersistencePort;
import com.emazon.stockService.domain.spi.CategoryPersistencePort;
import com.emazon.stockService.domain.useCase.ArticleUseCase;
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
    private final BrandRepository brandRepository;
    private final BrandEntityMapper brandEntityMapper;
    private final ArticleRepository articleRepository;
    private final ArticleEntityMapper articleEntityMapper;

    @Bean
    public CategoryPersistencePort categoryPersistencePort() {
        return new CategoryJpaAdapter(categoryRepository, categoryEntityMapper);
    }

    @Bean
    public CategoryServicePort categoryServicePort(CategoryPersistencePort categoryPersistencePort) {
        return new CategoryUseCase(categoryPersistencePort);
    }

    @Bean
    public BrandPersistencePort brandPersistencePort() {
        return new BrandJpaAdapter(brandRepository, brandEntityMapper);
    }

    @Bean
    public BrandServicePort brandServicePort(BrandPersistencePort brandPersistencePort) {
        return new BrandUseCase(brandPersistencePort);
    }
    @Bean
    public ArticlePersistencePort articlePersistencePort() {
        return new ArticleJpaAdapter(articleRepository, articleEntityMapper);
    }

    @Bean
    public ArticleServicePort articleServicePort(ArticlePersistencePort articlePersistencePort) {
        return new ArticleUseCase(articlePersistencePort);
    }
}
