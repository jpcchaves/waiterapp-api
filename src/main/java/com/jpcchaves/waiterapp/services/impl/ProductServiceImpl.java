package com.jpcchaves.waiterapp.services.impl;

import com.jpcchaves.waiterapp.entities.Product;
import com.jpcchaves.waiterapp.exceptions.ResourceNotFoundException;
import com.jpcchaves.waiterapp.payload.dtos.product.ProductDto;
import com.jpcchaves.waiterapp.payload.dtos.product.ProductRequestDto;
import com.jpcchaves.waiterapp.repositories.ProductRepository;
import com.jpcchaves.waiterapp.services.ProductService;
import com.jpcchaves.waiterapp.utils.mapper.MapperUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;
    private final MapperUtils mapper;

    public ProductServiceImpl(ProductRepository repository, MapperUtils mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }


    @Override
    public ProductDto create(ProductRequestDto productRequestDto) {
        Product product = mapper.parseObject(productRequestDto, Product.class);
        Product savedProduct = repository.save(product);
        return mapper.parseObject(savedProduct, ProductDto.class);
    }

    @Override
    public List<ProductDto> getAll() {
        List<Product> products = repository.findAll();
        List<ProductDto> productDtos = mapper.parseListObjects(products, ProductDto.class);
        return productDtos;
    }

    @Override
    public ProductDto getById(Long id) {
        Product product = repository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found for id " + id));
        ProductDto productDto = mapper.parseObject(product, ProductDto.class);
        return productDto;
    }

    @Override
    public ProductDto update(Long id, ProductRequestDto productRequestDto) {
        Product product = repository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found for id " + id));

        product.setName(productRequestDto.getName());
        product.setDescription(productRequestDto.getDescription());
        product.setPrice(productRequestDto.getPrice());

        repository.save(product);

        ProductDto productDto = mapper.parseObject(product, ProductDto.class);

        return productDto;
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Product not found for id " + id);
        }

        repository.deleteById(id);
    }
}
