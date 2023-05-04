package com.jpcchaves.waiterapp.payload.dtos.order;

public class OrderStatusDto {
    private Integer status;

    public OrderStatusDto() {
    }

    public OrderStatusDto(Integer status) {
        this.status = status;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
