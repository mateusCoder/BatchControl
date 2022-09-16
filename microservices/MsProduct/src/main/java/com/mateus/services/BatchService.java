package com.mateus.services;

import com.mateus.dtos.batch.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.net.URI;

public interface BatchService {
    Page<BatchDto> findAll(Pageable page);

    URI create(BatchFormPostDto batchFormPostDto);

    BatchLeavingDto updateFromRetail(BatchFormPutFromRetailDto batchFormPutFromRetailDto);

    BatchLeavingDto updateFromWholesale(BatchFormPutFromWholesaleDto batchFormPutFromWholesaleDto);
}
