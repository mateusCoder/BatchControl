package com.mateus.services;

import com.mateus.dtos.batch.BatchDto;
import com.mateus.dtos.batch.BatchFormPostDto;
import com.mateus.dtos.batch.BatchFormPutFromRetailDto;
import com.mateus.dtos.batch.BatchFormPutFromWholesaleDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.net.URI;

public interface BatchService {
    Page<BatchDto> findAll(Pageable page);

    URI create(BatchFormPostDto batchFormPostDto);

    BatchDto updateFromRetail(Long id, BatchFormPutFromRetailDto batchFormPutFromRetailDto);

    BatchDto updateFromWholesale(Long id, BatchFormPutFromWholesaleDto batchFormPutFromWholesaleDto);
}
