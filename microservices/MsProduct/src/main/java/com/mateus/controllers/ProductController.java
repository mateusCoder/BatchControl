package com.mateus.controllers;

import com.mateus.dtos.product.ProductDto;
import com.mateus.dtos.product.ProductFormCreateDto;
import com.mateus.dtos.product.ProductFormDto;
import com.mateus.services.impl.ProductServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/products")
public class ProductController {

    private final ProductServiceImpl productService;
    @GetMapping
    public ResponseEntity<Page<ProductDto>> findAll(@PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable page){
        return ResponseEntity.ok().body(productService.findAll(page));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> findOne(@PathVariable Long id){
        return ResponseEntity.ok(productService.findOne(id));
    }

    @PostMapping()
    public ResponseEntity<ProductDto> create(@Valid @RequestBody ProductFormCreateDto productFormDto){
        return ResponseEntity.created(productService.create(productFormDto)).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> update(@PathVariable Long id, @Valid @RequestBody ProductFormDto productFormDto){
        return ResponseEntity.ok(productService.update(id, productFormDto));
    }
}
