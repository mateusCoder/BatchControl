package com.mateus.services;

import com.mateus.dtos.StoreDto;
import com.mateus.dtos.StoreFormDto;
import com.mateus.dtos.StoreFormPostDto;

import java.net.URI;

public interface StoreService {
    StoreDto findOne(Long id);

    URI create(StoreFormPostDto storeFormPostDto);

    StoreDto update(Long id, StoreFormDto storeFormDto);
}
