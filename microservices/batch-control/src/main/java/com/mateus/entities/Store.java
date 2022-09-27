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
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String companyName;

    private String phoneNumber;

    private String cnpj;

    private String city;

    private boolean active;

    @OneToMany()
    @JoinColumn(name = "Products_ID")
    private List<Product> products =  new ArrayList<>();

    public List<Product> getProducts(){
        return products;
    }

    public void setProducts(Product product){
        products.add(product);
    }
}
