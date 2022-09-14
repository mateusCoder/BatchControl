package com.mateus.services;

import com.mateus.dtos.product.ProductDto;
import com.mateus.dtos.product.ProductFormCreateDto;
import com.mateus.dtos.product.ProductFormDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.net.URI;

public interface ProductService {
    Page<ProductDto> findAll(Pageable page);

    ProductDto findOne(Long id);

    URI create(ProductFormCreateDto productFormDto);

    ProductDto update(Long id, ProductFormDto productFormDto);
}
