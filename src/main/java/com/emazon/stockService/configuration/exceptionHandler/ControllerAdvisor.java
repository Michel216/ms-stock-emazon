package com.emazon.stockService.configuration.exceptionHandler;

import com.emazon.stockService.domain.exception.CategoryNotFoundException;
import com.emazon.stockService.domain.exception.DuplicateCategoryNameException;
import com.emazon.stockService.domain.exception.InvalidCategoryDescriptionException;
import com.emazon.stockService.domain.exception.InvalidCategoryNameException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleCategoryNotFoundException(
            CategoryNotFoundException ex, WebRequest request) {
        ExceptionResponse response = new ExceptionResponse(
                LocalDateTime.now(),
                ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({InvalidCategoryNameException.class, InvalidCategoryDescriptionException.class})
    public ResponseEntity<ExceptionResponse> handleInvalidCategoryException(
            RuntimeException ex, WebRequest request) {
        ExceptionResponse response = new ExceptionResponse(
                LocalDateTime.now(),
                ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DuplicateCategoryNameException.class)
    public ResponseEntity<ExceptionResponse> handleDuplicateCategoryNameException(
            DuplicateCategoryNameException ex, WebRequest request) {
        ExceptionResponse response = new ExceptionResponse(
                LocalDateTime.now(),
                ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }
}