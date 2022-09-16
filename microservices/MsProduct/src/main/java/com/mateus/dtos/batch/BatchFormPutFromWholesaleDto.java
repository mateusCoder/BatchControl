package com.mateus.dtos.batch;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BatchFormPutFromWholesaleDto {

    private Long idProduct;

    private Integer numberBatches;

    private Integer productsPerBatch;
}
