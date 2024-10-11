package com.emazon.stockService.domain.model;

import com.emazon.stockService.domain.utils.DomainConstants;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

import static java.util.Objects.requireNonNull;

@Getter
@Setter
public class Category {
    private Long id;
    private String name;
    private String description;
    private Set<Article> articles = new HashSet<>();

    public Category(Long id, String name, String description) {
        this.id = id;
        this.name = requireNonNull(name, DomainConstants.FIELD_NAME_NULL_MESSAGE);
        this.description = requireNonNull(description, DomainConstants.FIELD_DESCRIPTION_NULL_MESSAGE);
    }

    public Category() {

    }
}
