package com.jpcchaves.waiterapp.payload.dtos.lineitem;

public class LineItemDto {
    private Integer quantity;
    private Long orderId;
    private Long productId;

    public LineItemDto() {
    }

    public LineItemDto(Integer quantity, Long orderId, Long productId) {
        this.quantity = quantity;
        this.orderId = orderId;
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
