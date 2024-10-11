// src/main/java/com/emazon/stockService/adapters/driving/http/dto/response/ArticleResponse.java

package com.emazon.stockService.adapters.driving.http.dto.response;

import java.math.BigDecimal;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class ArticleResponse {
    private Long id;
    private String name;
    private String description;
    private int quantity;
    private BigDecimal price;
    private BrandResponse brand;
    private Set<CategoryResponse> categories;


    public ArticleResponse() {
    }

    public ArticleResponse(Long id, String name, String description, int quantity, BigDecimal price, BrandResponse brand, Set<CategoryResponse> categories) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
        this.brand = brand;
        this.categories = categories;
    }

}
