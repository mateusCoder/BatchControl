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
@RequestMapping("/v1/products/{id}/batches")
public class BatchController {

    private final BatchServiceImpl batchService;

    @GetMapping
    public ResponseEntity<Page<BatchDto>> findAll(@PathVariable Long id,
                                                  @PageableDefault(sort = "amount", direction = Sort.Direction.DESC)
                                                  Pageable page){
        return ResponseEntity.ok(batchService.findAll(id, page));
    }

    @PostMapping
    public ResponseEntity<BatchDto> create(@PathVariable Long id, @Valid @RequestBody BatchFormPostDto batchFormPostDto){
        return ResponseEntity.created(batchService.create(id, batchFormPostDto)).build();
    }

    @PutMapping("/retail")
    public ResponseEntity<BatchLeavingDto> updateFromRetail(@PathVariable Long id, @Valid @RequestBody BatchFormPutFromRetailDto batchFormPutFromRetailDto){
        return ResponseEntity.ok(batchService.updateFromRetail(id, batchFormPutFromRetailDto));
    }

    @PutMapping("/wholesale")
    public ResponseEntity<BatchLeavingDto> updateFromWholesale(@PathVariable Long id, @Valid @RequestBody BatchFormPutFromWholesaleDto batchFormPutFromWholesaleDto){
        return ResponseEntity.ok(batchService.updateFromWholesale(id, batchFormPutFromWholesaleDto));
    }
}
