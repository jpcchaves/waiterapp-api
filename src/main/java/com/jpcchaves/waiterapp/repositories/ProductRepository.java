package com.jpcchaves.waiterapp.repositories;

import com.jpcchaves.waiterapp.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByStatus(Integer status);

    Optional<Product> findByStatusAndId(Integer status, Long id);
}
