package com.jpcchaves.waiterapp.payload.dtos.order;

import com.jpcchaves.waiterapp.payload.dtos.lineitem.LineItemDataDto;

import java.util.List;

public class OrderRequestDto {
    private String orderDetails;
    private List<LineItemDataDto> lineItems;

    public OrderRequestDto() {
    }

    public OrderRequestDto(String orderDetails,
                           List<LineItemDataDto> lineItems) {
        this.orderDetails = orderDetails;
        this.lineItems = lineItems;
    }

    public String getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(String orderDetails) {
        this.orderDetails = orderDetails;
    }

    public List<LineItemDataDto> getLineItems() {
        return lineItems;
    }

    public void setLineItems(List<LineItemDataDto> lineItems) {
        this.lineItems = lineItems;
    }
}
