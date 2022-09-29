package com.mateus.builder;

import com.mateus.dtos.StoreDto;
import com.mateus.dtos.StoreFormDto;
import com.mateus.dtos.StoreFormPostDto;
import com.mateus.entities.Product;
import com.mateus.entities.Store;

import java.util.ArrayList;
import java.util.List;

public class StoreBuilder {

    private static final Long id = 1L;

    private static final String companyName = "Atacado Novaes - Loja 1";

    private static final String phoneNumber = "(19) 3833 - 6521";

    private static final String cnpj = "75.315.333/0267-60";

    private static final String city = "Mogi Mirim - SP";

    private static final boolean active = true;

    private static final List<Product> products = new ArrayList(List.of(ProductBuilder.getProduct(),
            ProductBuilder.getProduct()));

    public static Store getStore(){
        return Store.builder()
                .id(id)
                .companyName(companyName)
                .phoneNumber(phoneNumber)
                .cnpj(cnpj)
                .city(city)
                .active(active)
                .products(products)
                .build();
    }

    public static StoreDto getStoreDto(){
        return StoreDto.builder()
                .companyName(companyName)
                .phoneNumber(phoneNumber)
                .cnpj(cnpj)
                .city(city)
                .active(active)
                .build();
    }

    public static StoreFormDto getStoreFormDto(){
        return StoreFormDto.builder()
                .companyName(companyName)
                .phoneNumber(phoneNumber)
                .cnpj(cnpj)
                .city(city)
                .active(active)
                .build();
    }

    public static StoreFormPostDto getStoreFormPostDto(){
        return StoreFormPostDto.builder()
                .companyName(companyName)
                .phoneNumber(phoneNumber)
                .cnpj(cnpj)
                .city(city)
                .build();
    }

}
