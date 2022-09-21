package com.mateus.builder;

import com.mateus.dtos.product.ProductDto;
import com.mateus.dtos.product.ProductFormCreateDto;
import com.mateus.dtos.product.ProductFormDto;
import com.mateus.entities.Batch;
import com.mateus.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.time.LocalDate;
import java.util.List;

public class ProductBuilder {

    private static final Long id = 1L;

    private static final String name = "Bolo em pacote";

    private static final String description = "Bole gostoso de morango";

    private static final boolean active = true;

    private static final Batch batchA = new Batch("1a09b2022a",
            "Lote de Bolo - Morango",
            LocalDate.of(2023, 7, 20),
            20,
            ProductBuilder.getProduct());

    private static final Batch batchB = new Batch("1a09b2022b",
            "Lote de Bolo - Morango",
            LocalDate.of(2023, 8, 20),
            50,
            ProductBuilder.getProduct());
    private static final List<Batch> batches = List.of(BatchBuilder.getBatch(), batchA, batchB);

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
