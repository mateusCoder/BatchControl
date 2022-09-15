package com.mateus.controllers;

import com.mateus.dtos.batch.BatchDto;
import com.mateus.dtos.batch.BatchFormPostDto;
import com.mateus.dtos.batch.BatchFormPutFromRetailDto;
import com.mateus.dtos.batch.BatchFormPutFromWholesaleDto;
import com.mateus.services.impl.BatchServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<BatchDto> create(@RequestBody BatchFormPostDto batchFormPostDto){
        return ResponseEntity.created(batchService.create(batchFormPostDto)).build();
    }

    @PutMapping("/retail/{id}")
    public ResponseEntity<BatchDto> updateFromRetail(@PathVariable Long id, @RequestBody BatchFormPutFromRetailDto batchFormPutFromRetailDto){
        return ResponseEntity.ok(batchService.updateFromRetail(id, batchFormPutFromRetailDto));
    }

    @PutMapping("/wholesale/{id}")
    public ResponseEntity<BatchDto> updateFromWholesale(@PathVariable Long id, @RequestBody BatchFormPutFromWholesaleDto batchFormPutFromWholesaleDto){
        return ResponseEntity.ok(batchService.updateFromWholesale(id, batchFormPutFromWholesaleDto));
    }
}
