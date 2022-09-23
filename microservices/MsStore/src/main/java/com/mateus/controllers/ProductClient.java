package com.mateus.controllers;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(url = "http://localhost:8081/v1/products", name = "product-api")
public interface ProductClient {

    @GetMapping()
    Page<ProductDto> listAll();
}
