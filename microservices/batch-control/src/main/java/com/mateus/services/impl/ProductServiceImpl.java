package com.mateus.services.impl;

import com.mateus.dtos.product.ProductDto;
import com.mateus.dtos.product.ProductFormCreateDto;
import com.mateus.dtos.product.ProductFormDto;
import com.mateus.entities.Product;
import com.mateus.entities.Store;
import com.mateus.exceptions.ObjectNotFound;
import com.mateus.repositories.ProductRepository;
import com.mateus.repositories.StoreRepository;
import com.mateus.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final StoreRepository storeRepository;

    private final ModelMapper mapper;

    @Override
    public Page<ProductDto> findAll(Long id, Pageable page) {
        checkStoreExistence(id);
        Page<Product> products = productRepository.findByStoreId(id, page);
        return new PageImpl<>(products.stream().map(
                e -> mapper.map(e, ProductDto.class)).collect(Collectors.toList()));
    }

    @Override
    public ProductDto findOne(Long id, Long productId) {
        Product product = checkProductExistenceFromStore(id, productId);
        return mapper.map(product, ProductDto.class);
    }

    @Override
    public URI create(Long id, ProductFormCreateDto productFormCreateDto) {
        Store store = checkStoreExistence(id);
        Product product = mapper.map(productFormCreateDto, Product.class);
        product.setActive(true);
        product.setStoreId(id);
        productRepository.save(product);
        store.setProducts(product);
        storeRepository.save(store);
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").build(product.getId());
    }

    @Override
    public ProductDto update(Long id, Long productId, ProductFormDto productFormDto) {
        checkProductExistenceFromStore(id, productId);
        Product product = mapper.map(productFormDto, Product.class);
        product.setId(productId);
        product.setStoreId(id);
        productRepository.save(product);
        return mapper.map(product, ProductDto.class);
    }

    public Product checkProductExistenceFromStore(Long id, Long productId){
        return productRepository.findByIdAndStoreId(productId, id).
                orElseThrow(() -> new ObjectNotFound("Object Not Found!"));
    }

    public Store checkStoreExistence(Long id){
        return storeRepository.findById(id).orElseThrow(() -> new ObjectNotFound("Store not found!"));
    }
}
