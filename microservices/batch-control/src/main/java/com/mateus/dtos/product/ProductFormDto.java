package com.mateus.dtos.product;

import com.mateus.entities.Batch;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductFormDto {

    @NotBlank
    @Size(max=100,min=3)
    private String name;

    private String description;

    @NotNull
    private boolean active;
}
