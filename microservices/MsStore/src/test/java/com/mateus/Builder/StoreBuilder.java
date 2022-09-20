package com.mateus.Builder;

import com.mateus.dtos.StoreDto;
import com.mateus.dtos.StoreFormDto;
import com.mateus.dtos.StoreFormPostDto;
import com.mateus.entities.Store;

public class StoreBuilder {

    private static final Long id = 1L;

    private static final String companyName = "Atacado Novaes - Loja 1";

    private static final String phoneNumber = "(19) 3833 - 6521";

    private static final String cnpj = "75.315.333/0267-60";

    private static final String city = "Mogi Mirim - SP";

    private static final boolean active = true;

    public Store getStore(){
        return Store.builder()
                .id(id)
                .companyName(companyName)
                .phoneNumber(phoneNumber)
                .cnpj(cnpj)
                .city(city)
                .active(active)
                .build();
    }

    public StoreDto getStoreDto(){
        return StoreDto.builder()
                .companyName(companyName)
                .phoneNumber(phoneNumber)
                .cnpj(cnpj)
                .city(city)
                .active(active)
                .build();
    }

    public StoreFormDto getStoreFormDto(){
        return StoreFormDto.builder()
                .companyName(companyName)
                .phoneNumber(phoneNumber)
                .cnpj(cnpj)
                .city(city)
                .active(active)
                .build();
    }

    public StoreFormPostDto getStoreFormPostDto(){
        return StoreFormPostDto.builder()
                .companyName(companyName)
                .phoneNumber(phoneNumber)
                .cnpj(cnpj)
                .city(city)
                .build();
    }

}
