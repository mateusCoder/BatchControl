package com.mateus.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class StoreFormPostDto {

    private String companyName;

    private String phoneNumber;

    private String cnpj;

    private String city;

}
