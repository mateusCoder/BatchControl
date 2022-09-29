package com.mateus.controllers;

import com.mateus.builder.ProductBuilder;
import com.mateus.builder.StoreBuilder;
import com.mateus.dtos.product.ProductDto;
import com.mateus.services.impl.ProductServiceImpl;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@AutoConfigureTestDatabase
class ProductControllerTest {

    @InjectMocks
    ProductController productController;

    @Mock
    ProductServiceImpl productService;

    @Spy
    ModelMapper mapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void whenFindAllThenReturnResponseEntityPageableProductDto() {
        when(productService.findAll(anyLong(), (Pageable) any())).thenReturn(ProductBuilder.getProductDtoPageable());

        Pageable page = PageRequest.of(0, 100);
        ResponseEntity<Page<ProductDto>> response = productController.findAll(StoreBuilder.getStore().getId(),
                page);

        assertNotNull(response);
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void whenFindOneThenReturnResponseEntityProductDto() {
        when(productService.findOne(anyLong(), anyLong())).thenReturn(ProductBuilder.getProductDto());

        ResponseEntity<ProductDto> response = productController.findOne(StoreBuilder.getStore().getId(),
                ProductBuilder.getProduct().getId());

        assertNotNull(response);
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void whenCreateThenReturnSaveResponseEntityProductDto() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/v1/store/1/products/{id}")
                .build(ProductBuilder.getProduct().getId());

        when(productService.create(anyLong(), any())).thenReturn(uri);

        ResponseEntity<ProductDto> response = productController.create(StoreBuilder.getStore().getId(),
                ProductBuilder.getProductFormCreateDto());

        assertNotNull(response);
        assertEquals(uri.toString(), "http://localhost/v1/store/1/products/1");
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void whenUpdateThenReturnUpdateResponseEntityProductDto() {
        when(productService.update(anyLong(), anyLong(), any())).thenReturn(ProductBuilder.getProductDto());

        ResponseEntity<ProductDto> response = productController.update(StoreBuilder.getStore().getId(), ProductBuilder.getProduct().getId(), ProductBuilder.getProductFormDto());

        assertNotNull(response);
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}