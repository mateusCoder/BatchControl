package com.mateus.repositories;

import com.mateus.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Page<Product> findByStoreId(Long store, Pageable page);

    Optional<Product> findByIdAndStoreId(Long productId, Long id);
}
