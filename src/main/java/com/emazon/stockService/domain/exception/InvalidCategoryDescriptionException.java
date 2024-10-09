package com.emazon.stockService.domain.exception;

public class InvalidCategoryDescriptionException extends RuntimeException {
    public InvalidCategoryDescriptionException(String message) {
        super(message);
    }
}