package com.mateus.entities;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Batch {

    @Id
    private String id;

    private String description;

    private LocalDate expirationDate;

    private Integer amount;

    @ManyToOne
    @JoinColumn(name = "Product_ID")
    private Product product;
}
