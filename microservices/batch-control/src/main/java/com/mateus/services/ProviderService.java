package com.mateus.services;

import com.mateus.dtos.provider.ProviderDto;
import com.mateus.dtos.provider.ProviderFormDto;
import com.mateus.dtos.provider.ProviderFormPostDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.net.URI;

public interface ProviderService {
    Page<ProviderDto> findAll(Pageable page);

    URI create(ProviderFormPostDto providerFormDto);

    ProviderDto update(Long id, ProviderFormDto providerFormDto);
}
