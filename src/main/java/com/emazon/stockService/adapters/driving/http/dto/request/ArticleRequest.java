// src/main/java/com/emazon/stockService/adapters/driving/http/dto/request/ArticleRequest.java

package com.emazon.stockService.adapters.driving.http.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class ArticleRequest {
    @NotBlank(message = "Article name cannot be empty")
    private String name;

    @NotBlank(message = "Article description cannot be empty")
    private String description;

    @NotNull(message = "Article quantity cannot be null")
    @Positive(message = "Article quantity must be positive")
    private Integer quantity;

    @NotNull(message = "Article price cannot be null")
    @Positive(message = "Article price must be positive")
    private BigDecimal price;

    @NotNull(message = "Brand ID cannot be null")
    private Long brandId;

    @NotNull(message = "Categories cannot be null")
    @Size(min = 1, max = 3, message = "Article must have between 1 and 3 categories")
    private Set<Long> categoryIds;

}