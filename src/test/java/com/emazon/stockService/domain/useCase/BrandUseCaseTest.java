package com.emazon.stockService.domain.useCase;

import com.emazon.stockService.domain.exception.*;
import com.emazon.stockService.domain.model.Brand;
import com.emazon.stockService.domain.spi.BrandPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class BrandUseCaseTest {

    @Mock
    private BrandPersistencePort brandPersistencePort;

    private BrandUseCase brandUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        brandUseCase = new BrandUseCase(brandPersistencePort);
    }

    @Test
    void createBrand_ValidBrand_ShouldCreateSuccessfully() {
        Brand brand = new Brand(null, "Test Brand", "Test Description");
        when(brandPersistencePort.existsByName(anyString())).thenReturn(false);
        when(brandPersistencePort.saveBrand(any(Brand.class))).thenReturn(brand);

        Brand result = brandUseCase.createBrand(brand);

        assertNotNull(result);
        assertEquals(brand.getName(), result.getName());
        assertEquals(brand.getDescription(), result.getDescription());
        verify(brandPersistencePort).saveBrand(brand);
        verify(brandPersistencePort).existsByName(brand.getName());
    }

    @Test
    void createBrand_EmptyName_ShouldThrowInvalidNameException() {
        Brand brand = new Brand(null, "", "Test Description");

        assertThrows(InvalidNameException.class, () -> brandUseCase.createBrand(brand));
    }

    @Test
    void createBrand_LongName_ShouldThrowInvalidNameException() {
        Brand brand = new Brand(null, "A".repeat(51), "Test Description");

        assertThrows(InvalidNameException.class, () -> brandUseCase.createBrand(brand));
    }

    @Test
    void createBrand_EmptyDescription_ShouldThrowInvalidDescriptionException() {
        Brand brand = new Brand(null, "Test Brand", "");

        assertThrows(InvalidDescriptionException.class, () -> brandUseCase.createBrand(brand));
    }

    @Test
    void createBrand_LongDescription_ShouldThrowInvalidDescriptionException() {
        Brand brand = new Brand(null, "Test Brand", "A".repeat(121));

        assertThrows(InvalidDescriptionException.class, () -> brandUseCase.createBrand(brand));
    }

    @Test
    void createBrand_DuplicateName_ShouldThrowDuplicateNameException() {
        Brand brand = new Brand(null, "Test Brand", "Test Description");
        when(brandPersistencePort.existsByName(anyString())).thenReturn(true);

        assertThrows(DuplicateNameException.class, () -> brandUseCase.createBrand(brand));
        verify(brandPersistencePort).existsByName(brand.getName());
    }

    @Test
    void getBrandById_ExistingId_ShouldReturnBrand() {
        Long id = 1L;
        Brand brand = new Brand(id, "Test Brand", "Test Description");
        when(brandPersistencePort.findById(id)).thenReturn(Optional.of(brand));

        Brand result = brandUseCase.getBrandById(id);

        assertNotNull(result);
        assertEquals(id, result.getId());
        assertEquals(brand.getName(), result.getName());
        assertEquals(brand.getDescription(), result.getDescription());
        verify(brandPersistencePort).findById(id);
    }

    @Test
    void getBrandById_NonExistingId_ShouldThrowNotFoundException() {
        Long id = 1L;
        when(brandPersistencePort.findById(id)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> brandUseCase.getBrandById(id));
        verify(brandPersistencePort).findById(id);
    }
    @Test
    void listBrands_ShouldReturnPagedBrands() {
        Pageable pageable = PageRequest.of(0, 10);
        List<Brand> brands = Arrays.asList(
                new Brand(1L, "Brand 1", "Description 1"),
                new Brand(2L, "Brand 2", "Description 2")
        );
        Page<Brand> brandPage = new PageImpl<>(brands, pageable, brands.size());

        when(brandPersistencePort.findAll(any(Pageable.class))).thenReturn(brandPage);

        Page<Brand> result = brandUseCase.listBrands(pageable, "name", "asc");

        assertNotNull(result);
        assertEquals(2, result.getContent().size());
        assertEquals("Brand 1", result.getContent().get(0).getName());
        assertEquals("Brand 2", result.getContent().get(1).getName());
        verify(brandPersistencePort).findAll(any(Pageable.class));
    }
}
