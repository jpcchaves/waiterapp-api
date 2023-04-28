package com.jpcchaves.waiterapp.infra.config.beans;

import com.jpcchaves.waiterapp.domain.adapters.services.ProdutServiceImpl;
import com.jpcchaves.waiterapp.domain.ports.interfaces.ProductServicePort;
import com.jpcchaves.waiterapp.domain.ports.repositories.ProductRepositoryPort;
import com.jpcchaves.waiterapp.infra.utils.mapper.MapperUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductServicePortBean {

    private final MapperUtils mapperUtils;

    public ProductServicePortBean(MapperUtils mapperUtils) {
        this.mapperUtils = mapperUtils;
    }

    @Bean
    ProductServicePort productServicePort(ProductRepositoryPort productRepositoryPort) {
        return new ProdutServiceImpl(productRepositoryPort, mapperUtils);
    }
}
