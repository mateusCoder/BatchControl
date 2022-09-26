package com.mateus.controllers;

import com.mateus.dtos.batch.*;
import com.mateus.services.impl.BatchServiceImpl;
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
@RequestMapping("/v1/batches")
public class BatchController {

    private final BatchServiceImpl batchService;

    @GetMapping
    public ResponseEntity<Page<BatchDto>> findAll(@PageableDefault(sort = "amount", direction = Sort.Direction.DESC)Pageable page){
        return ResponseEntity.ok(batchService.findAll(page));
    }

    @PostMapping
    public ResponseEntity<BatchDto> create(@Valid @RequestBody BatchFormPostDto batchFormPostDto){
        return ResponseEntity.created(batchService.create(batchFormPostDto)).build();
    }

    @PutMapping("/retail")
    public ResponseEntity<BatchLeavingDto> updateFromRetail(@Valid @RequestBody BatchFormPutFromRetailDto batchFormPutFromRetailDto){
        return ResponseEntity.ok(batchService.updateFromRetail(batchFormPutFromRetailDto));
    }

    @PutMapping("/wholesale")
    public ResponseEntity<BatchLeavingDto> updateFromWholesale(@Valid @RequestBody BatchFormPutFromWholesaleDto batchFormPutFromWholesaleDto){
        return ResponseEntity.ok(batchService.updateFromWholesale(batchFormPutFromWholesaleDto));
    }
}
