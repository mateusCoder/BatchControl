package com.mateus.repositories;

import com.mateus.entities.Provider;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProviderRespository extends JpaRepository<Provider, Long> {
}
