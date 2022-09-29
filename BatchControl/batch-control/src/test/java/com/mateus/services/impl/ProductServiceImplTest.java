package com.mateus.services.impl;

import com.mateus.builder.ProductBuilder;
import com.mateus.builder.StoreBuilder;
import com.mateus.dtos.product.ProductDto;
import com.mateus.entities.Product;
import com.mateus.entities.Store;
import com.mateus.repositories.ProductRepository;
import com.mateus.repositories.StoreRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.net.URI;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@AutoConfigureTestDatabase
class ProductServiceImplTest {

    @InjectMocks
    ProductServiceImpl productService;

    @InjectMocks
    StoreServiceImpl storeService;

    @Mock
    ProductRepository productRepository;

    @Mock
    StoreRepository storeRepository;

    @Spy
    ModelMapper mapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void whenFindAllThenReturnPageableProductDto() {
        when(storeRepository.findById(anyLong())).thenReturn(Optional.of(StoreBuilder.getStore()));
        when(productRepository.findByStoreId(anyLong(), (Pageable) any())).thenReturn(ProductBuilder.getProductPageable());

        Pageable page = PageRequest.of(0, 100);
        Page<ProductDto> response = productService.findAll(StoreBuilder.getStore().getId(), page);

        assertNotNull(response);
        assertEquals(1, response.getTotalElements());
    }

    @Test
    void whenFindOneThenReturnProductDto() {
        when(productRepository.findByIdAndStoreId(anyLong(), anyLong())).thenReturn(Optional.of(ProductBuilder.getProduct()));

        ProductDto response = productService.findOne(StoreBuilder.getStore().getId(),
                ProductBuilder.getProduct().getId());

        assertNotNull(response);
        assertEquals(ProductDto.class, response.getClass());
        assertEquals(ProductBuilder.getProduct().getName(), response.getName());
    }

    @Test
    void whenCreateThenReturnSaveProductDto() {
        MockHttpServletRequest  request =new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        when(storeRepository.findById(anyLong())).thenReturn(Optional.of(StoreBuilder.getStore()));
        when(productRepository.save(any())).thenReturn(ProductBuilder.getProduct());
        when(storeRepository.save(any())).thenReturn(StoreBuilder.getStore());

        URI response = productService.create(StoreBuilder.getStore().getId(),
                ProductBuilder.getProductFormCreateDto());

        verify(productRepository, times(1)).save(any(Product.class));
    }

    @Test
    void whenUpdateThenReturnUpdateProductDto() {
        when(productRepository.findByIdAndStoreId(anyLong(), anyLong())).thenReturn(Optional.of(ProductBuilder.getProduct()));
        when(productRepository.save(any())).thenReturn(ProductBuilder.getProduct());

        ProductDto response = productService.update(StoreBuilder.getStore().getId(),
                ProductBuilder.getProduct().getId(), ProductBuilder.getProductFormDto());

        assertNotNull(response);
        assertEquals(ProductDto.class, response.getClass());
        assertEquals(ProductBuilder.getProductDto().getName(), response.getName());

    }

    @Test
    void whenFindOneThenReturnCheckProductExistenceFromStore() {
        when(productRepository.findByIdAndStoreId(anyLong(), anyLong())).thenReturn(Optional.of(ProductBuilder.getProduct()));

        Product response = productService.checkProductExistenceFromStore(StoreBuilder.getStore().getId(),
                ProductBuilder.getProduct().getId());

        assertNotNull(response);
        assertEquals(Product.class, response.getClass());
        assertEquals(ProductBuilder.getProductDto().getName(), response.getName());
    }

    @Test
    void whenFindOneThenReturnCheckStoreExistence() {
        when(storeRepository.findById(anyLong())).thenReturn(Optional.of(StoreBuilder.getStore()));

        Store response = storeService.checkStoreExistence(StoreBuilder.getStore().getId());

        assertNotNull(response);
        assertEquals(Store.class, response.getClass());
        assertEquals(StoreBuilder.getStore().getCompanyName(), response.getCompanyName());
    }
}