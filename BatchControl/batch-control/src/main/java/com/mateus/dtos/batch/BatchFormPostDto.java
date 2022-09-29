package com.mateus.dtos.batch;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BatchFormPostDto {

    @NotBlank
    private String id;

    private String description;

    @NotNull
    private LocalDate expirationDate;

    @NotNull
    private Integer amount;

}
