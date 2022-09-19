package com.mateus.builder;

import com.mateus.dtos.product.ProductDto;
import com.mateus.dtos.product.ProductFormCreateDto;
import com.mateus.dtos.product.ProductFormDto;
import com.mateus.entities.Batch;
import com.mateus.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.ArrayList;
import java.util.List;

public class ProductBuilder {

    private static final Long id = 1L;

    private static final String name = "Bolo em pacote";

    private static final String description = "Bole gostoso de morango";

    private static final boolean active = true;

    private static final List<Batch> batches = new ArrayList<>(List.of(BatchBuilder.getBatch()));

    public static Product getProduct(){
        return Product.builder()
                .id(id)
                .name(name)
                .description(description)
                .active(active)
                .batches(batches)
                .build();
    }

    public static ProductDto getProductDto(){
        return ProductDto.builder()
                .name(name)
                .description(description)
                .active(active)
                .build();
    }

    public static ProductFormCreateDto getProductFormCreateDto(){
        return ProductFormCreateDto.builder()
                .name(name)
                .description(description)
                .build();
    }

    public static ProductFormDto getProductFormDto(){
        return ProductFormDto.builder()
                .name(name)
                .description(description)
                .active(active)
                .build();
    }

    public static Page<Product> getProductPageable(){
        return new PageImpl<>(List.of(getProduct()));
    }

    public static Page<ProductDto> getProductDtoPageable(){
        return new PageImpl<>(List.of(getProductDto()));
    }
}
