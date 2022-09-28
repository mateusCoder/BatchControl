package com.mateus.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private boolean active;

    @OneToMany()
    private List<Batch> batches =  new ArrayList<>();

    public void setBatches(Batch batch){
        batches.add(batch);
    }

    public List<Batch> getBatches(){
        return batches;
    }

    private Long storeId;
}
