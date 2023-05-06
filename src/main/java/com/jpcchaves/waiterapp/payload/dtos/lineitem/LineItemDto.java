package com.jpcchaves.waiterapp.payload.dtos.lineitem;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.jpcchaves.waiterapp.payload.dtos.order.OrderResponseDto;
import com.jpcchaves.waiterapp.payload.dtos.product.ProductDto;

public class LineItemDto {
    private Long id;
    private Integer quantity;
    private Double subTotal;
    private Double selledPrice;
    @JsonBackReference
    private OrderResponseDto order;
    private ProductDto product;


    public LineItemDto() {
    }

    public LineItemDto(Long id,
                       Integer quantity,
                       Double subTotal,
                       Double selledPrice,
                       OrderResponseDto order,
                       ProductDto product) {
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

    public OrderResponseDto getOrder() {
        return order;
    }

    public void setOrder(OrderResponseDto order) {
        this.order = order;
    }

    public ProductDto getProduct() {
        return product;
    }

    public void setProduct(ProductDto product) {
        this.product = product;
    }

    public Double getSelledPrice() {
        return selledPrice;
    }

    public void setSelledPrice(Double selledPrice) {
        this.selledPrice = selledPrice;
    }
}
