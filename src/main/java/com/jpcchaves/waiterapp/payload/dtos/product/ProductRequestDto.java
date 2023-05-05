package com.jpcchaves.waiterapp.payload.dtos.product;

public class ProductRequestDto {
    private String name;
    private String description;
    private Double price;

    public ProductRequestDto() {
    }

    public ProductRequestDto(String name,
                             String description,
                             Double price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
