package com.mateus.services.impl;

import com.mateus.dtos.StoreDto;
import com.mateus.dtos.StoreFormDto;
import com.mateus.dtos.StoreFormPostDto;
import com.mateus.entities.Store;
import com.mateus.exceptions.ObjectNotFound;
import com.mateus.repositories.StoreRepository;
import com.mateus.services.StoreService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RequiredArgsConstructor
@Service
public class StoreServiceImpl implements StoreService {

    private final StoreRepository storeRepository;

    private final ModelMapper mapper;

    @Override
    public StoreDto findOne(Long id) {
        return mapper.map(checkStoreExistence(id), StoreDto.class);
    }

    @Override
    public URI create(StoreFormPostDto storeFormPostDto) {
        Store store = mapper.map(storeFormPostDto, Store.class);
        store.setActive(true);
        storeRepository.save(store);
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").build(store.getId());
    }

    @Override
    public StoreDto update(Long id, StoreFormDto storeFormDto) {
        checkStoreExistence(id);
        Store store = mapper.map(storeFormDto, Store.class);
        store.setId(id);
        storeRepository.save(store);
        return mapper.map(store, StoreDto.class);
    }

    public Store checkStoreExistence(Long id){
        return storeRepository.findById(id).orElseThrow(() -> new ObjectNotFound("Store Not Found!"));
    }
}
