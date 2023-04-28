package com.jpcchaves.waiterapp.domain.dtos;

public class StockDto {
    private Integer quantity;

    public StockDto() {
    }

    public StockDto(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
