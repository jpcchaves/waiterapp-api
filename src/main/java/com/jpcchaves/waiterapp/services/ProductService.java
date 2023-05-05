package com.jpcchaves.waiterapp.services;

import com.jpcchaves.waiterapp.payload.dtos.product.ProductDto;
import com.jpcchaves.waiterapp.payload.dtos.product.ProductRequestDto;

import java.util.List;

public interface ProductService {
    ProductDto create(ProductRequestDto productRequestDto);

    List<ProductDto> getAll();

    ProductDto getById(Long id);

    ProductDto update(Long id, ProductRequestDto productRequestDto);

    void delete(Long id);
}
