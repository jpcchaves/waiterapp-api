package com.jpcchaves.waiterapp.payload.dtos.lineitem;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.jpcchaves.waiterapp.entities.Order;
import com.jpcchaves.waiterapp.entities.Product;

public class LineItemDto {
    private Long id;
    private Integer quantity;
    private Double subTotal;
    @JsonBackReference
    private Order order;
    private Product product;


    public LineItemDto() {
    }

    public LineItemDto(Long id,
                       Integer quantity,
                       Double subTotal,
                       Order order,
                       Product product) {
        this.id = id;
        this.quantity = quantity;
        this.subTotal = subTotal;
        this.order = order;
        this.product = product;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(Double subTotal) {
        this.subTotal = subTotal;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
