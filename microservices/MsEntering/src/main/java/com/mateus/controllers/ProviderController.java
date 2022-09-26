package com.mateus.controllers;

import com.mateus.repositories.ProviderRespository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/provider")
public class ProviderController {

    private final ProviderRespository providerRespository;

}
