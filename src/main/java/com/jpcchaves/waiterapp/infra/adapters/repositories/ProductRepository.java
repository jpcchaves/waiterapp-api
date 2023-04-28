package com.jpcchaves.waiterapp.infra.adapters.repositories;

import com.jpcchaves.waiterapp.domain.Product;
import com.jpcchaves.waiterapp.domain.dtos.StockDto;
import com.jpcchaves.waiterapp.domain.ports.repositories.ProductRepositoryPort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepository implements ProductRepositoryPort {

    private final SpringProductRepository springProductRepository;

    public ProductRepository(SpringProductRepository springProductRepository) {
        this.springProductRepository = springProductRepository;
    }

    @Override
    public List<Product> getAll() {
        return null;
    }

    @Override
    public Product save(Product product) {
        return null;
    }

    @Override
    public Optional<Product> getById(Long id) {
        return Optional.empty();
    }

    @Override
    public void updateStock(StockDto stock) {

    }
}
