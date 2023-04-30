package com.jpcchaves.waiterapp.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "line_item")
public class LineItem implements Serializable {

    private static final long serialVersionUID = -7781440183472527897L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer quantity;

    @ManyToOne(
            fetch = FetchType.EAGER
    )
    @JoinColumn(
            name = "order_id",
            nullable = false
    )
    @JsonBackReference
    private Order order;

    @ManyToOne(
            fetch = FetchType.EAGER
    )
    @JoinColumn(
            name = "product_id",
            nullable = false
    )
    @JsonManagedReference
    private Product product;

    public LineItem() {
    }

    public LineItem(Long id, Integer quantity, Order order, Product product) {
        this.id = id;
        this.quantity = quantity;
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
