package com.emazon.stockService.adapters.driving.http.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BrandRequest {
    @NotBlank(message = "Brand name cannot be empty")
    @Size(max = 50, message = "Brand name cannot exceed 50 characters")
    private String name;

    @NotBlank(message = "Brand description cannot be empty")
    @Size(max = 120, message = "Brand description cannot exceed 120 characters")
    private String description;

}