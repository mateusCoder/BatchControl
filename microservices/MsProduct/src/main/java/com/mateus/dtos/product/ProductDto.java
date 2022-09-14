package com.mateus.dtos.product;

import com.mateus.entities.Batch;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDto {

    private String name;

    private String description;

    private Batch batch;

    private boolean active;
}
