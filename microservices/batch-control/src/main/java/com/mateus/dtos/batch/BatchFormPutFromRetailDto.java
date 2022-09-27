package com.mateus.dtos.batch;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BatchFormPutFromRetailDto {

    @NotNull
    private Long idProduct;

    @NotNull
    private Integer amount;
}