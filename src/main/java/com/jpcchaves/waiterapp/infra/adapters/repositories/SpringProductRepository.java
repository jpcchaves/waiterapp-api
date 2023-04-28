package com.jpcchaves.waiterapp.infra.adapters.repositories;

import com.jpcchaves.waiterapp.infra.adapters.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringProductRepository extends JpaRepository<ProductEntity, Long> {
    Optional<ProductEntity> getByCode(Long code);
}
