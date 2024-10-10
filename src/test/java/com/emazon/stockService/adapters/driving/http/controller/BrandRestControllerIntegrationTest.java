package com.emazon.stockService.adapters.driving.http.controller;

import com.emazon.stockService.domain.api.BrandServicePort;
import com.emazon.stockService.domain.model.Brand;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BrandRestController.class)
class BrandRestControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BrandServicePort brandServicePort;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createBrand_ValidBrand_ShouldReturnCreatedBrand() throws Exception {
        Brand brand = new Brand(1L, "Test Brand", "Test Description");
        when(brandServicePort.createBrand(any(Brand.class))).thenReturn(brand);

        mockMvc.perform(post("/api/brands")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(brand)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Test Brand"))
                .andExpect(jsonPath("$.description").value("Test Description"));
    }

    @Test
    void getBrandById_ExistingId_ShouldReturnBrand() throws Exception {
        Long id = 1L;
        Brand brand = new Brand(id, "Test Brand", "Test Description");
        when(brandServicePort.getBrandById(id)).thenReturn(brand);

        mockMvc.perform(get("/api/brands/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value("Test Brand"))
                .andExpect(jsonPath("$.description").value("Test Description"));
    }

    @Test
    void listBrands_ShouldReturnPagedBrands() throws Exception {
        Page<Brand> brandPage = new PageImpl<>(Arrays.asList(
                new Brand(1L, "Brand 1", "Description 1"),
                new Brand(2L, "Brand 2", "Description 2")
        ), PageRequest.of(0, 10), 2);

        when(brandServicePort.listBrands(any(), any(), any())).thenReturn(brandPage);

        mockMvc.perform(get("/api/brands")
                        .param("page", "0")
                        .param("size", "10")
                        .param("sortBy", "name")
                        .param("sortDirection", "asc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content[0].id").value(1))
                .andExpect(jsonPath("$.content[0].name").value("Brand 1"))
                .andExpect(jsonPath("$.content[1].id").value(2))
                .andExpect(jsonPath("$.content[1].name").value("Brand 2"))
                .andExpect(jsonPath("$.totalElements").value(2));
    }
}