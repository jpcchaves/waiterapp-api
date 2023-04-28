package com.jpcchaves.waiterapp.domain;

public class Product {
    private Long code;
    private String name;
    private Double price;
    private Integer quantity;

    public Product() {
    }

    public Product(Long code, String name, Double price, Integer quantity) {
        this.code = code;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
}
