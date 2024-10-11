package com.emazon.stockService.adapters.driven.jpa.mysql.entity;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "categories")
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String name;

    @Column(nullable = false, length = 90)
    private String description;

    @ManyToMany(mappedBy = "categories", fetch = FetchType.LAZY)
    private Set<ArticleEntity> articles = new HashSet<>();


    public void addArticle(ArticleEntity article) {
        this.articles.add(article);
        article.getCategories().add(this);
    }

    public void removeArticle(ArticleEntity article) {
        this.articles.remove(article);
        article.getCategories().remove(this);
    }
}
