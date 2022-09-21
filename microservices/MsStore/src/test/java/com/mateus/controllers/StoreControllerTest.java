package com.mateus.controllers;

import com.mateus.Builder.StoreBuilder;
import com.mateus.dtos.StoreDto;
import com.mateus.services.impl.StoreServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
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
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

class StoreControllerTest {

    @InjectMocks
    StoreController storeController;

    @Mock
    StoreServiceImpl storeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void whenFindOneThenReturnResponseEntityStoreDto() {
        when(storeService.findOne(anyLong())).thenReturn(StoreBuilder.getStoreDto());

        ResponseEntity<StoreDto> response = storeController.findOne(StoreBuilder.getStore().getId());

        assertNotNull(response);
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void whenCreateThenReturnSaveResponseEntityStoreDto() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/v1/stores/{id}")
                .build(StoreBuilder.getStore().getId());

        when(storeService.create(any())).thenReturn(uri);

        ResponseEntity<StoreDto> response = storeController.create(StoreBuilder.getStoreFormPostDto());

        assertNotNull(response);
        assertEquals(uri.toString(), "http://localhost/v1/stores/1");
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void whenUpdateThenReturnUpdateResponseEntityStoreDto() {
        when(storeService.update(anyLong(), any())).thenReturn(StoreBuilder.getStoreDto());

        ResponseEntity<StoreDto> response = storeController.update(StoreBuilder.getStore().getId(), StoreBuilder.getStoreFormDto());

        assertNotNull(response);
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}