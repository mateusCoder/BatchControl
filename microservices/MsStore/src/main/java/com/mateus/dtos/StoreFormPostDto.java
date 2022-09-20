package com.mateus.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CNPJ;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class StoreFormPostDto {

    @NotBlank
    @Size(max=100,min=3)
    private String companyName;

    @NotBlank
    @Size(max=100,min=10)
    private String phoneNumber;

    @CNPJ
    @NotBlank
    private String cnpj;

    @NotBlank
    private String city;

}
