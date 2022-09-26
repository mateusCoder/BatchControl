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
@RequestMapping("/v1/store/{id}/products")
public class ProductController {

    private final ProductServiceImpl productService;

    @GetMapping
    public ResponseEntity<Page<ProductDto>> findAll(@PathVariable Long id, @PageableDefault(sort = "id", direction = Sort.Direction.ASC, size = 1000) Pageable page){
        return ResponseEntity.ok().body(productService.findAll(id, page));
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductDto> findOne(@PathVariable Long id, @PathVariable Long productId){
        return ResponseEntity.ok(productService.findOne(id, productId));
    }

    @PostMapping()
    public ResponseEntity<ProductDto> create(@PathVariable Long id, @Valid @RequestBody ProductFormCreateDto productFormDto){
        return ResponseEntity.created(productService.create(id, productFormDto)).build();
    }

    @PutMapping("/{productId}")
    public ResponseEntity<ProductDto> update(@PathVariable Long id, @PathVariable Long productId, @Valid @RequestBody ProductFormDto productFormDto){
        return ResponseEntity.ok(productService.update(id, productId, productFormDto));
    }
}
