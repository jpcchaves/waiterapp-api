package com.jpcchaves.waiterapp.payload.dtos.order;

import com.jpcchaves.waiterapp.Enum.OrderStatus;
import com.jpcchaves.waiterapp.entities.LineItem;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

public class OrderResponseDto {
    private Long orderId;
    private UUID orderCode;
    private String orderDetails;
    private OrderStatus status;
    private Date createdAt;
    private Date conclusionDate;
    private Double orderTotal;
    private Boolean isPaid;
    private Boolean isDone;
    private Set<LineItem> lineItems;

    public OrderResponseDto() {
    }

    public OrderResponseDto(Long orderId,
                            UUID orderCode,
                            String orderDetails,
                            OrderStatus status,
                            Date createdAt,
                            Date conclusionDate,
                            Double orderTotal,
                            Boolean isPaid,
                            Boolean isDone,
                            Set<LineItem> lineItems) {
        this.orderId = orderId;
        this.orderCode = orderCode;
        this.orderDetails = orderDetails;
        this.status = status;
        this.createdAt = createdAt;
        this.conclusionDate = conclusionDate;
        this.orderTotal = orderTotal;
        this.isPaid = isPaid;
        this.isDone = isDone;
        this.lineItems = lineItems;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public UUID getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(UUID orderCode) {
        this.orderCode = orderCode;
    }

    public String getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(String orderDetails) {
        this.orderDetails = orderDetails;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getConclusionDate() {
        return conclusionDate;
    }

    public void setConclusionDate(Date conclusionDate) {
        this.conclusionDate = conclusionDate;
    }

    public Double getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(Double orderTotal) {
        this.orderTotal = orderTotal;
    }

    public Boolean getPaid() {
        return isPaid;
    }

    public void setPaid(Boolean paid) {
        isPaid = paid;
    }

    public Boolean getDone() {
        return isDone;
    }

    public void setDone(Boolean done) {
        isDone = done;
    }

    public Set<LineItem> getLineItems() {
        return lineItems;
    }

    public void setLineItems(Set<LineItem> lineItems) {
        this.lineItems = lineItems;
    }
}
