package com.mateus.controllers;

import com.mateus.builder.ProductBuilder;
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
    void findAll() {
        when(productService.findAll((Pageable) any())).thenReturn(ProductBuilder.getProductDtoPageable());

        Pageable page = PageRequest.of(0, 100);
        ResponseEntity<Page<ProductDto>> response = productController.findAll(page);

        assertNotNull(response);
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void findOne() {
        when(productService.findOne(anyLong())).thenReturn(ProductBuilder.getProductDto());

        ResponseEntity<ProductDto> response = productController.findOne(ProductBuilder.getProduct().getId());

        assertNotNull(response);
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void create() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/v1/products/{id}")
                .build(ProductBuilder.getProduct().getId());

        when(productService.create(any())).thenReturn(uri);

        ResponseEntity<ProductDto> response = productController.create(ProductBuilder.getProductFormCreateDto());

        assertNotNull(response);
        assertEquals(uri.toString(), "http://localhost/v1/products/1");
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void update() {
        when(productService.update(anyLong(), any())).thenReturn(ProductBuilder.getProductDto());

        ResponseEntity<ProductDto> response = productController.update(ProductBuilder.getProduct().getId(), ProductBuilder.getProductFormDto());

        assertNotNull(response);
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}