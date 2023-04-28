package com.jpcchaves.waiterapp.app.adapters.controllers;

import com.jpcchaves.waiterapp.domain.dtos.ProductDto;
import com.jpcchaves.waiterapp.domain.ports.interfaces.ProductServicePort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductsController {
    private final ProductServicePort productServicePort;

    public ProductsController(ProductServicePort productServicePort) {
        this.productServicePort = productServicePort;
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAll() {
        return ResponseEntity.ok(productServicePort.getAll());
    }
}
