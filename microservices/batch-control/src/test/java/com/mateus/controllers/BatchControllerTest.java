package com.mateus.controllers;

import com.mateus.builder.BatchBuilder;
import com.mateus.dtos.batch.BatchDto;
import com.mateus.dtos.batch.BatchLeavingDto;
import com.mateus.services.impl.BatchServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class BatchControllerTest {

    @InjectMocks
    BatchController batchController;

    @Mock
    BatchServiceImpl batchService;

    @Spy
    ModelMapper mapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void whenFindAllThenReturnResponseEntityPageableBatchDto() {
        when(batchService.findAll((Pageable) any())).thenReturn(BatchBuilder.getBatchDtoPageable());

        Pageable page = PageRequest.of(0, 100);
        ResponseEntity<Page<BatchDto>> response = batchController.findAll(page);

        assertNotNull(response);
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void  whenCreateThenReturnSaveResponseEntityBatchDto() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/v1/batches/{id}")
                .build(BatchBuilder.getBatch().getId());

        when(batchService.create(any())).thenReturn(uri);

        ResponseEntity<BatchDto> response = batchController.create(BatchBuilder.getBatchFormPostDto());

        assertNotNull(response);
        assertEquals(uri.toString(), "http://localhost/v1/batches/" + BatchBuilder.getBatchDto().getId());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void  whenUpdateFromRetailThenReturnUpdateResponseEntityBatchDtoFromRetail() {
        when(batchService.updateFromRetail(any())).thenReturn(BatchBuilder.getBatchLeavingDto());

        ResponseEntity<BatchLeavingDto> response = batchController
                .updateFromRetail(BatchBuilder.getBatchFormPutFromRetailDto());

        assertNotNull(response);
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void  whenUpdateFromWholesaleThenReturnUpdateResponseEntityBatchDtoFromWholesale() {
        when(batchService.updateFromWholesale(any())).thenReturn(BatchBuilder.getBatchLeavingDto());

        ResponseEntity<BatchLeavingDto> response = batchController
                .updateFromWholesale(BatchBuilder.getBatchFormPutFromWholesaleDto());

        assertNotNull(response);
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(HttpStatus.OK, response.getStatusCode());

    }
}