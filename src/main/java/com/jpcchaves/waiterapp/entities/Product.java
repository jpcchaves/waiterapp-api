package com.jpcchaves.waiterapp.entities;

import com.jpcchaves.waiterapp.Enum.ProductStatus;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "products")
public class Product implements Serializable {

    private static final long serialVersionUID = -323000221643539689L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private Double price;
    private Integer status;

    public Product() {
    }

    public Product(Long id,
                   String name,
                   String description,
                   Double price,
                   Integer status) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.status = status;
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
        return ProductStatus.valueOf(status);
    }

    public void setStatus(ProductStatus status) {
        if (status != null) {
            this.status = status.getCode();
        }
    }
}
