package com.jpcchaves.waiterapp.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.jpcchaves.waiterapp.Enum.OrderStatus;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "orders")
@EntityListeners(AuditingEntityListener.class)
public class Order implements Serializable {

    @Serial
    private static final long serialVersionUID = -8304692593435400074L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID orderCode;

    private String orderDetails;

    private OrderStatus status;
    @CreatedDate
    private Date createdAt;
    private Date conclusionDate;

    private Double orderTotal;

    private Boolean isPaid;

    private Boolean isDone;

    @OneToMany(
            mappedBy = "order",
            cascade = CascadeType.DETACH
    )
    @JsonManagedReference
    private Set<LineItem> lineItems = new HashSet<>();

    public Order() {
    }

    public Order(Long orderId,
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

