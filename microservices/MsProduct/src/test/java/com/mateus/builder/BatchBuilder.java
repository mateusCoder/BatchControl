package com.mateus.builder;

import com.mateus.dtos.batch.*;
import com.mateus.entities.Batch;
import com.mateus.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BatchBuilder {

    private static final String id = "1a09b2022c";

    private static final String description = "Lote de Bolo - Morango";

    private static final LocalDate expirationDate = LocalDate.of(2023, 9, 20);

    private static final Integer amount = 50;

    private static final Product product = ProductBuilder.getProduct();

    private static final Long productId = 1L;

    private static final Integer numberBatches = 1;

    private static final Integer productsPerBatch = 50;

    private static String[] vetIdBatch = {"1"};

    private static final List<String> idBatch = new ArrayList<>(Arrays.asList(vetIdBatch));


    public static Batch getBatch(){
        return Batch.builder()
                .id(id)
                .description(description)
                .expirationDate(expirationDate)
                .amount(amount)
                .product(product)
                .build();
    }

    public static BatchDto getBatchDto(){
        return BatchDto.builder()
                .id(id)
                .description(description)
                .expirationDate(expirationDate)
                .amount(amount)
                .build();
    }

    public static BatchFormPostDto getBatchFormPostDto(){
        return BatchFormPostDto.builder()
                .id(id)
                .description(description)
                .expirationDate(expirationDate)
                .amount(amount)
                .productId(productId)
                .build();
    }

    public static BatchFormPutFromRetailDto getBatchFormPutFromRetailDto(){
        return BatchFormPutFromRetailDto.builder()
                .idProduct(productId)
                .amount(amount)
                .build();
    }

    public static BatchFormPutFromWholesaleDto getBatchFormPutFromWholesaleDto(){
        return BatchFormPutFromWholesaleDto.builder()
                .idProduct(productId)
                .numberBatches(numberBatches)
                .productsPerBatch(productsPerBatch)
                .build();
    }

    public static BatchLeavingDto getBatchLeavingDto(){
        return BatchLeavingDto.builder()
                .productName(ProductBuilder.getProduct().getName())
                .idBatch(idBatch)
                .build();
    }

    public static Page<Batch> getBatchPageable(){
        return new PageImpl<>(List.of(getBatch()));
    }

    public static Page<BatchDto> getBatchDtoPageable(){
        return new PageImpl<>(List.of(getBatchDto()));
    }
}
