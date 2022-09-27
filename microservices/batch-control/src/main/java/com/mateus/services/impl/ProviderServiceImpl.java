package com.mateus.services.impl;

import com.mateus.dtos.provider.ProviderDto;
import com.mateus.dtos.provider.ProviderFormDto;
import com.mateus.dtos.provider.ProviderFormPostDto;
import com.mateus.entities.Provider;
import com.mateus.exceptions.ObjectNotFound;
import com.mateus.repositories.ProviderRespository;
import com.mateus.services.ProviderService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RequiredArgsConstructor
@Service
public class ProviderServiceImpl implements ProviderService {

    private final ProviderRespository providerRespository;

    private final ModelMapper mapper;

    @Override
    public Page<ProviderDto> findAll(Pageable page) {
        Page<Provider> providers = providerRespository.findAll(page);
        return providers.map(e -> mapper.map(e, ProviderDto.class));
    }

    @Override
    public URI create(ProviderFormPostDto providerFormDto) {
        Provider provider = mapper.map(providerFormDto, Provider.class);
        provider.setActive(true);
        providerRespository.save(provider);
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").build(provider.getId());
    }

    @Override
    public ProviderDto update(Long id, ProviderFormDto providerFormDto) {
        providerRespository.findById(id).orElseThrow(() -> new ObjectNotFound("Provider Not Found!"));
        Provider provider = mapper.map(providerFormDto, Provider.class);
        provider.setId(id);
        providerRespository.save(provider);

        return mapper.map(provider, ProviderDto.class);
    }
}
