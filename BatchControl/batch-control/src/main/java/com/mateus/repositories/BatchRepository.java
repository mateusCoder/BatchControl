package com.mateus.repositories;

import com.mateus.entities.Batch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BatchRepository extends JpaRepository<Batch, String> {
    Page<Batch> findByProductId(Long id, Pageable page);
}
