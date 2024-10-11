package com.emazon.stockService.adapters.driven.jpa.mysql.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "articles")
public class ArticleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private BigDecimal price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id", nullable = false)
    private BrandEntity brand;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "article_categories",
            joinColumns = @JoinColumn(name = "article_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<CategoryEntity> categories = new HashSet<>();

    public ArticleEntity() {}

    public ArticleEntity(Long id, String name, String description, int quantity, BigDecimal price, BrandEntity brand) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
        this.brand = brand;
    }

    public void addCategory(CategoryEntity category) {
        this.categories.add(category);
        category.getArticles().add(this);
    }

    public void removeCategory(CategoryEntity category) {
        this.categories.remove(category);
        category.getArticles().remove(this);
    }

    public Set<CategoryEntity> getCategories() {
        return categories;
    }
}
