package com.mateus.dtos.batch;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BatchFormPostDto {

    private String id;

    private String description;

    private LocalDate expirationDate;

    private Integer amount;

    private Long productId;
}
