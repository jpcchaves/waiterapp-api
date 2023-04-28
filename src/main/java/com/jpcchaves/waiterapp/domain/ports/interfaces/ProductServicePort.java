package com.jpcchaves.waiterapp.domain.ports.interfaces;

import com.jpcchaves.waiterapp.domain.Product;
import com.jpcchaves.waiterapp.domain.dtos.ProductDto;
import com.jpcchaves.waiterapp.domain.dtos.StockDto;

import java.util.List;

public interface ProductServicePort {
    List<ProductDto> getAll();
    ProductDto create(ProductDto product);
    void updateStock(Long id, StockDto stock) throws RuntimeException;
}
