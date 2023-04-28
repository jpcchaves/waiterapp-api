package com.jpcchaves.waiterapp.domain.adapters.services;

import com.jpcchaves.waiterapp.domain.Product;
import com.jpcchaves.waiterapp.domain.dtos.ProductDto;
import com.jpcchaves.waiterapp.domain.dtos.StockDto;
import com.jpcchaves.waiterapp.domain.ports.interfaces.ProductServicePort;
import com.jpcchaves.waiterapp.domain.ports.repositories.ProductRepositoryPort;
import com.jpcchaves.waiterapp.infra.utils.mapper.MapperUtils;

import java.util.List;
import java.util.stream.Collectors;

public class ProdutServiceImpl implements ProductServicePort {

    private final ProductRepositoryPort productRepositoryPort;
    private final MapperUtils mapperUtils;

    public ProdutServiceImpl(ProductRepositoryPort productRepositoryPort,
                             MapperUtils mapperUtils) {
        this.productRepositoryPort = productRepositoryPort;
        this.mapperUtils = mapperUtils;
    }

    @Override
    public List<ProductDto> getAll() {
        List<Product> products = this.productRepositoryPort.getAll();
        List<ProductDto> productsDtos = mapperUtils.parseListObjects(products, ProductDto.class);
        return productsDtos;
    }

    @Override
    public ProductDto create(ProductDto product) {
        Product productToCreate = mapperUtils.parseObject(product, Product.class);
        Product productCreated = this.productRepositoryPort.save(productToCreate);
        return mapperUtils.parseObject(productCreated, ProductDto.class);
    }

    @Override
    public void updateStock(Long id, StockDto stock) throws RuntimeException {
        Product product = this.productRepositoryPort.getById(id).orElseThrow(() -> new RuntimeException(""));

        product.setQuantity(stock.getQuantity());

        productRepositoryPort.save(product);
    }
}
