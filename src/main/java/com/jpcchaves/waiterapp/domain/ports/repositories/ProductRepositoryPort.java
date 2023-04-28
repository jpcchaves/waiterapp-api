package com.jpcchaves.waiterapp.domain.ports.repositories;

import com.jpcchaves.waiterapp.domain.Product;
import com.jpcchaves.waiterapp.domain.dtos.ProductDto;
import com.jpcchaves.waiterapp.domain.dtos.StockDto;

import java.util.List;
import java.util.Optional;

public interface ProductRepositoryPort {
    List<Product> getAll();
    Product save(Product product);
    Optional<Product> getById(Long id);
    void updateStock(StockDto stock);
}
