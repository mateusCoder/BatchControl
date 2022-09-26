package com.mateus.services.impl;

import com.mateus.dtos.product.ProductDto;
import com.mateus.dtos.product.ProductFormCreateDto;
import com.mateus.dtos.product.ProductFormDto;
import com.mateus.entities.Product;
import com.mateus.exceptions.ObjectNotFound;
import com.mateus.repositories.ProductRepository;
import com.mateus.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final ModelMapper mapper;

    @Override
    public Page<ProductDto> findAll(Pageable page) {
        Page<Product> products = productRepository.findAll(page);
        return new PageImpl<>(products.stream().map(e -> mapper.map(e, ProductDto.class)).collect(Collectors.toList()));
    }

    @Override
    public ProductDto findOne(Long id) {
        Product product = checkProductExistence(id);
        return mapper.map(product, ProductDto.class);
    }

    @Override
    public URI create(ProductFormCreateDto productFormCreateDto) {
        Product product = mapper.map(productFormCreateDto, Product.class);
        product.setActive(true);
        productRepository.save(product);
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").build(product.getId());
    }

    @Override
    public ProductDto update(Long id, ProductFormDto productFormDto) {
        checkProductExistence(id);
        Product product = mapper.map(productFormDto, Product.class);
        product.setId(id);
        productRepository.save(product);
        return mapper.map(product, ProductDto.class);
    }

    public Product checkProductExistence(Long id){
        return productRepository.findById(id).orElseThrow(() -> new ObjectNotFound("Product Not Found!"));
    }
}
