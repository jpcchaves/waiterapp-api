package com.jpcchaves.waiterapp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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

    @CreatedDate
    private Date createdAt;

    private Double orderTotal;

    @OneToMany(
            mappedBy = "order",
            cascade = CascadeType.DETACH
    )
    @JsonManagedReference
    private List<LineItem> lineItems = new ArrayList<>();

    public Order() {
    }

    public Order(Long orderId,
                 UUID orderCode,
                 Double orderTotal,
                 Date createdAt,
                 List<LineItem> lineItems) {
        this.orderId = orderId;
        this.orderCode = orderCode;
        this.orderTotal = orderTotal;
        this.createdAt = createdAt;
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

    public Double getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(Double orderTotal) {
        this.orderTotal = orderTotal;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public List<LineItem> getLineItems() {
        return lineItems;
    }

    public void setLineItems(List<LineItem> lineItems) {
        this.lineItems = lineItems;
    }
}

