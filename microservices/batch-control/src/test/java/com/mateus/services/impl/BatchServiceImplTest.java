package com.mateus.services.impl;

import com.mateus.builder.BatchBuilder;
import com.mateus.builder.ProductBuilder;
import com.mateus.builder.StoreBuilder;
import com.mateus.dtos.batch.BatchDto;
import com.mateus.dtos.batch.BatchLeavingDto;
import com.mateus.dtos.product.ProductDto;
import com.mateus.entities.Batch;
import com.mateus.entities.Product;
import com.mateus.repositories.BatchRepository;
import com.mateus.repositories.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@AutoConfigureTestDatabase
class BatchServiceImplTest {

    @InjectMocks
    BatchServiceImpl batchService;

    @InjectMocks
    ProductServiceImpl productService;

    @Mock
    BatchRepository batchRepository;

    @Mock
    ProductRepository productRepository;

    @Spy
    ModelMapper mapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void whenFindAllThenReturnPageableBatchDto() {
        when(batchRepository.findAll((Pageable) any())).thenReturn(BatchBuilder.getBatchPageable());

        Pageable page = PageRequest.of(0, 100);
        Page<BatchDto> response = batchService.findAll(page);

        assertNotNull(response);
        assertEquals(PageImpl.class, response.getClass());
        assertEquals(1, response.getTotalElements());

    }

    @Test
    void whenCreateThenReturnSaveBatchtDto() {
        MockHttpServletRequest request =new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        when(productRepository.findById(anyLong())).thenReturn(Optional.of(ProductBuilder.getProduct()));
        when(batchRepository.findById(anyString())).thenReturn(Optional.of(BatchBuilder.getBatch()));
        when(batchRepository.save(any())).thenReturn(BatchBuilder.getBatch());

        URI response = batchService.create(BatchBuilder.getBatchFormPostDto());

        verify(batchRepository, times(1)).save(any(Batch.class));
    }

    @Test
    void whenUpdateThenReturnUpdateBatchRetailDto() {
        when(productRepository.findById(anyLong())).thenReturn(Optional.of(ProductBuilder.getProduct()));
        when(batchRepository.findById(anyString())).thenReturn(Optional.of(BatchBuilder.getBatch()));
        when(batchRepository.save(any())).thenReturn(BatchBuilder.getBatch());

        BatchLeavingDto response = batchService.updateFromRetail(BatchBuilder.getBatchFormPutFromRetailDto());

        assertNotNull(response);
    }

    @Test
    void whenUpdateThenReturnUpdateWholesaleDto() {
        when(productRepository.findById(anyLong())).thenReturn(Optional.of(ProductBuilder.getProduct()));
        when(batchRepository.findById(anyString())).thenReturn(Optional.of(BatchBuilder.getBatch()));
        when(batchRepository.save(any())).thenReturn(BatchBuilder.getBatch());

        BatchLeavingDto response = batchService.updateFromWholesale(BatchBuilder.getBatchFormPutFromWholesaleDto());

        assertNotNull(response);
    }

    @Test
    void whenFindOneThenReturnCheckProductExistence() {
        when(productRepository.findByIdAndStoreId(anyLong(), anyLong())).thenReturn(Optional.of(ProductBuilder.getProduct()));

        Product response = productService.checkProductExistenceFromStore(StoreBuilder.getStore().getId(),
                ProductBuilder.getProduct().getId());

        assertNotNull(response);
        assertEquals(Product.class, response.getClass());
        assertEquals(ProductBuilder.getProductDto().getName(), response.getName());
    }

    @Test
    void whenFindOneThenReturnCheckBatchExistence() {
        when(batchRepository.findById(anyString())).thenReturn(Optional.of(BatchBuilder.getBatch()));

        Batch response = batchService.checkBatchExistence(BatchBuilder.getBatch().getId());

        assertNotNull(response);
        assertEquals(Batch.class, response.getClass());
        assertEquals(BatchBuilder.getBatch().getId(), response.getId());
    }

    @Test
    void whenSumExistencesBatchThenUpdateBatchAmount(){
        when(batchRepository.findById(anyString())).thenReturn(Optional.of(BatchBuilder.getBatch()));
        when(batchRepository.save(any())).thenReturn(BatchBuilder.getBatch());

        batchService.sumExistencesBatch(BatchBuilder.getBatch().getId(), BatchBuilder.getBatch().getAmount());

        verify(batchRepository, times(1)).save(any());
    }

    @Test
    void whenFindBatchesWithAmountByProductThenReturnListOfBatches() {
        when(productRepository.findById(anyLong())).thenReturn(Optional.of(ProductBuilder.getProduct()));

        List<Batch> response = batchService.findBatchesWithAmountByProduct(ProductBuilder.getProduct().getId());

        assertNotNull(response);
        assertEquals(ArrayList.class, response.getClass());
    }

    @Test
    void whenSubtractAmountBatchThenReturnListOfBatchesChanged() {
        when(batchRepository.findById(anyString())).thenReturn(Optional.of(BatchBuilder.getBatch()));
        when(batchRepository.save(any())).thenReturn(BatchBuilder.getBatch());

        List<String> response = batchService.subtractAmountBatch(10, ProductBuilder.getProduct().getBatches());

        assertEquals(ArrayList.class, response.getClass());
        assertNotNull(response);
    }
}