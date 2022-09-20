package com.mateus.services.impl;

import com.mateus.Builder.StoreBuilder;
import com.mateus.dtos.StoreDto;
import com.mateus.entities.Store;
import com.mateus.repositories.StoreRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.swing.text.html.Option;
import java.net.URI;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@AutoConfigureTestDatabase
class StoreServiceImplTest {

    @InjectMocks
    StoreServiceImpl storeService;

    @Mock
    StoreRepository storeRepository;

    @Spy
    ModelMapper mapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void  whenFindOneThenReturnStoreDto() {
        when(storeRepository.findById(anyLong())).thenReturn(Optional.of(StoreBuilder.getStore()));

        StoreDto response = storeService.findOne(StoreBuilder.getStore().getId());

        assertNotNull(response);
        assertEquals(StoreDto.class, response.getClass());
        assertEquals(StoreBuilder.getStore().getCompanyName(), response.getCompanyName());
    }

    @Test
    void whenCreateThenReturnSaveStoreDto() {
        MockHttpServletRequest request =new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        when(storeRepository.save(any())).thenReturn(StoreBuilder.getStore());

        URI response = storeService.create(StoreBuilder.getStoreFormPostDto());

        verify(storeRepository, times(1)).save(any(Store.class));
    }

    @Test
    void whenUpdateThenReturnUpdateStoreDto() {
        when(storeRepository.findById(anyLong())).thenReturn(Optional.of(StoreBuilder.getStore()));
        when(storeRepository.save(any())).thenReturn(StoreBuilder.getStore());

        StoreDto response = storeService.update(StoreBuilder.getStore().getId(), StoreBuilder.getStoreFormDto());

        assertNotNull(response);
        assertEquals(StoreDto.class, response.getClass());
        assertEquals(StoreBuilder.getStore().getCompanyName(), response.getCompanyName());
    }

    @Test
    void whenFindOneThenReturnCheckStoreExistence() {
        when(storeRepository.findById(anyLong())).thenReturn(Optional.of(StoreBuilder.getStore()));

        Store response = storeService.checkStoreExistence(StoreBuilder.getStore().getId());

        assertNotNull(response);
        assertEquals(Store.class, response.getClass());
        assertEquals(StoreBuilder.getStore().getId(), response.getId());
    }
}