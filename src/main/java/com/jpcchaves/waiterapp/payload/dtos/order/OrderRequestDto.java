package com.jpcchaves.waiterapp.payload.dtos.order;

import com.jpcchaves.waiterapp.entities.LineItem;

import java.util.List;

public class OrderRequestDto {
    private String orderDetails;
    private List<LineItem> lineItems;

    public OrderRequestDto() {
    }

    public OrderRequestDto(String orderDetails,
                           List<LineItem> lineItems) {
        this.orderDetails = orderDetails;
        this.lineItems = lineItems;
    }

    public String getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(String orderDetails) {
        this.orderDetails = orderDetails;
    }

    public List<LineItem> getLineItems() {
        return lineItems;
    }

    public void setLineItems(List<LineItem> lineItems) {
        this.lineItems = lineItems;
    }
}
