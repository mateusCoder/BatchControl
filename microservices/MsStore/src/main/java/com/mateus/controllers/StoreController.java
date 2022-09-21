package com.mateus.controllers;

import com.mateus.dtos.StoreDto;
import com.mateus.dtos.StoreFormDto;
import com.mateus.dtos.StoreFormPostDto;
import com.mateus.services.impl.StoreServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/stores")
public class StoreController {

    private final StoreServiceImpl storeService;

    @GetMapping("/{id}")
    public ResponseEntity<StoreDto> findOne(@PathVariable Long id){
        return ResponseEntity.ok(storeService.findOne(id));
    }

    @PostMapping
    public ResponseEntity<StoreDto> create(@Valid @RequestBody StoreFormPostDto storeFormPostDto){
        return ResponseEntity.created(storeService.create(storeFormPostDto)).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<StoreDto> update(@PathVariable Long id, @Valid @RequestBody StoreFormDto storeFormDto){
        return ResponseEntity.ok(storeService.update(id, storeFormDto));
    }
}
