package com.emazon.stockService.domain.model;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Article {
    private Long id;
    private String name;
    private String description;
    private int quantity;
    private BigDecimal price;
    private Brand brand;
    private Set<Category> categories = new HashSet<>();

    public Article(Long id, String name, String description, int quantity, BigDecimal price, Brand brand) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
        this.brand = brand;
    }

    public void addCategory(Category category) {
        if (this.categories.size() < 3) {
            this.categories.add(category);
        } else {
            throw new IllegalArgumentException("Article cannot have more than 3 categories");
        }
    }

    public void removeCategory(Category category) {
        this.categories.remove(category);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return quantity == article.quantity &&
                Objects.equals(id, article.id) &&
                Objects.equals(name, article.name) &&
                Objects.equals(description, article.description) &&
                Objects.equals(price, article.price) &&
                Objects.equals(brand, article.brand) &&
                Objects.equals(categories, article.categories);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, quantity, price, brand, categories);
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", brand=" + brand +
                ", categories=" + categories +
                '}';
    }
}
