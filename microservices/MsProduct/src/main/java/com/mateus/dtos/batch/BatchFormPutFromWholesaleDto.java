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

    private String productName;

    private Integer numberBatches;

    private Integer productsPerBatch;
}
