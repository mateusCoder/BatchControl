package com.mateus.services;

import com.mateus.dtos.batch.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.net.URI;

public interface BatchService {
    Page<BatchDto> findAll(Long id, Pageable page);

    URI create(Long id, BatchFormPostDto batchFormPostDto);

    BatchLeavingDto updateFromRetail(Long id, BatchFormPutFromRetailDto batchFormPutFromRetailDto);

    BatchLeavingDto updateFromWholesale(Long id, BatchFormPutFromWholesaleDto batchFormPutFromWholesaleDto);
}
