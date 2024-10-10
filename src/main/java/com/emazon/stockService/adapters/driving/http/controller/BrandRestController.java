package com.emazon.stockService.adapters.driving.http.controller;

import com.emazon.stockService.adapters.driving.http.dto.request.BrandRequest;
import com.emazon.stockService.adapters.driving.http.dto.response.BrandResponse;
import com.emazon.stockService.adapters.driving.http.mapper.BrandRequestMapper;
import com.emazon.stockService.adapters.driving.http.mapper.BrandResponseMapper;
import com.emazon.stockService.domain.api.BrandServicePort;
import com.emazon.stockService.domain.model.Brand;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/brands")
public class BrandRestController {
    private final BrandServicePort brandServicePort;
    private final BrandRequestMapper brandRequestMapper;
    private final BrandResponseMapper brandResponseMapper;

    public BrandRestController(BrandServicePort brandServicePort,
                               BrandRequestMapper brandRequestMapper,
                               BrandResponseMapper brandResponseMapper) {
        this.brandServicePort = brandServicePort;
        this.brandRequestMapper = brandRequestMapper;
        this.brandResponseMapper = brandResponseMapper;
    }

    @PostMapping
    public ResponseEntity<BrandResponse> createBrand(@Valid @RequestBody BrandRequest brandRequest) {
        Brand brand = brandRequestMapper.toDomain(brandRequest);
        Brand createdBrand = brandServicePort.createBrand(brand);
        BrandResponse response = brandResponseMapper.toResponse(createdBrand);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BrandResponse> getBrandById(@PathVariable Long id) {
        Brand brand = brandServicePort.getBrandById(id);
        BrandResponse response = brandResponseMapper.toResponse(brand);
        return ResponseEntity.ok(response);
    }
}