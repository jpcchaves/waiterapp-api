package com.jpcchaves.waiterapp.domain.dtos;

public class ProductDto {
    private Long code;
    private String name;
    private Double price;
    private Integer quantity;

    public ProductDto() {
    }

    public ProductDto(Long code, String name, Double price, Integer quantity) {
        this.code = code;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
