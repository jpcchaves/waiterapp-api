package com.jpcchaves.waiterapp.payload.dtos.product;

import com.jpcchaves.waiterapp.Enum.ProductStatus;

import java.util.Date;

public class ProductDto {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private ProductStatus status;
    private Date inactivationDate;

    public ProductDto() {
    }

    public ProductDto(Long id,
                      String name,
                      String description,
                      Double price,
                      ProductStatus status,
                      Date inactivationDate) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.status = status;
        this.inactivationDate = inactivationDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public ProductStatus getStatus() {
        return status;
    }

    public void setStatus(ProductStatus status) {
        this.status = status;
    }

    public Date getInactivationDate() {
        return inactivationDate;
    }

    public void setInactivationDate(Date inactivationDate) {
        this.inactivationDate = inactivationDate;
    }
}
