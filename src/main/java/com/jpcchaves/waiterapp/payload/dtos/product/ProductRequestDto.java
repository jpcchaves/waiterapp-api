package com.jpcchaves.waiterapp.payload.dtos.product;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class ProductRequestDto {
    @NotBlank(message = "Product is a required field")
    private String name;
    @NotBlank(message = "Description is a required field")
    private String description;
    @Positive(message = "Price should be positive")
    @DecimalMin(message = "Price should be higher than 0.0", value = "0.1")
    @NotNull(message = "Price is a required field")
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
