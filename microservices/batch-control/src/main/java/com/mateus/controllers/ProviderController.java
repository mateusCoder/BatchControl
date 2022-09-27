package com.mateus.controllers;

import com.mateus.dtos.provider.ProviderDto;
import com.mateus.dtos.provider.ProviderFormDto;
import com.mateus.dtos.provider.ProviderFormPostDto;
import com.mateus.repositories.ProviderRespository;
import com.mateus.services.impl.ProviderServiceImpl;
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
@RequestMapping("/v1/providers")
public class ProviderController {

    private final ProviderServiceImpl providerService;

    @GetMapping
    public ResponseEntity<Page<ProviderDto>> getAll(@PageableDefault(sort = "company", direction = Sort.Direction.ASC) Pageable page){
        return ResponseEntity.ok(providerService.findAll(page));
    }

    @PostMapping
    public ResponseEntity<ProviderDto> create(@Valid @RequestBody ProviderFormPostDto providerFormDto){
        return ResponseEntity.created(providerService.create(providerFormDto)).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProviderDto> update(@PathVariable Long id, @Valid @RequestBody ProviderFormDto providerFormDto){
        return ResponseEntity.ok(providerService.update(id, providerFormDto));
    }

}
