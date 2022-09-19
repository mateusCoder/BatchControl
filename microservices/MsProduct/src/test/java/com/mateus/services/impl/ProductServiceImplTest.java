package com.mateus.services.impl;

import com.mateus.builder.ProductBuilder;
import com.mateus.dtos.product.ProductDto;
import com.mateus.entities.Product;
import com.mateus.repositories.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@AutoConfigureTestDatabase
class ProductServiceImplTest {

    @InjectMocks
    ProductServiceImpl productService;

    @Mock
    ProductRepository productRepository;

    @Spy
    ModelMapper mapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAll() {
        when(productRepository.findAll((Pageable) any())).thenReturn(ProductBuilder.getProductPageable());

        Pageable page = PageRequest.of(0, 100);
        Page<ProductDto> response = productService.findAll(page);

        assertNotNull(response);
        assertEquals(1, response.getTotalElements());
    }

    @Test
    void findOne() {
        when(productRepository.findById(anyLong())).thenReturn(Optional.of(ProductBuilder.getProduct()));

        ProductDto response = productService.findOne(ProductBuilder.getProduct().getId());

        assertNotNull(response);
        assertEquals(ProductDto.class, response.getClass());
        assertEquals(ProductBuilder.getProduct().getName(), response.getName());
    }

    @Test
    void create() {
        MockHttpServletRequest  request =new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        when(productRepository.save(any())).thenReturn(ProductBuilder.getProduct());

        URI response = productService.create(ProductBuilder.getProductFormCreateDto());

        verify(productRepository, times(1)).save(any(Product.class));
    }

    @Test
    void update() {
        when(productRepository.findById(anyLong())).thenReturn(Optional.of(ProductBuilder.getProduct()));
        when(productRepository.save(any())).thenReturn(ProductBuilder.getProduct());

        ProductDto response = productService.update(ProductBuilder.getProduct().getId(),
                ProductBuilder.getProductFormDto());

        assertNotNull(response);
        assertEquals(ProductDto.class, response.getClass());
        assertEquals(ProductBuilder.getProductDto().getName(), response.getName());

    }

    @Test
    void checkProductExistence() {
        when(productRepository.findById(anyLong())).thenReturn(Optional.of(ProductBuilder.getProduct()));

        Product response = productService.checkProductExistence(ProductBuilder.getProduct().getId());

        assertNotNull(response);
        assertEquals(Product.class, response.getClass());
        assertEquals(ProductBuilder.getProductDto().getName(), response.getName());
    }
}