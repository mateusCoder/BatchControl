package com.mateus.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProviderFormDto {

    private String company;

    private String trade;

    private String contact;

    private String cnpj;

    private String phoneNumber;
}
