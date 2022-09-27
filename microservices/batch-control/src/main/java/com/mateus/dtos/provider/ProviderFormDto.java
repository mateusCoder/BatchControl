package com.mateus.dtos.provider;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProviderFormDto {

    @NotBlank
    private String company;

    @NotBlank
    private String trade;

    @NotBlank
    private String contact;

    @NotBlank
    private String cnpj;

    @NotBlank
    private String phoneNumber;

    @NotNull
    private boolean active;

}
