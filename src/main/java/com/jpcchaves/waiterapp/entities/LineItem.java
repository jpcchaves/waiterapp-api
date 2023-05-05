package com.jpcchaves.waiterapp.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "line_item")
public class LineItem implements Serializable {

    private static final long serialVersionUID = -7781440183472527897L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double selledPrice;
    private Integer quantity;
    private Double subTotal;

    @ManyToOne(
            fetch = FetchType.EAGER,
            cascade = CascadeType.DETACH
    )
    @JoinColumn(
            name = "order_id",
            nullable = false
    )
    @JsonBackReference
    private Order order;

    @ManyToOne(
            fetch = FetchType.EAGER,
            cascade = CascadeType.DETACH
    )
    @JoinColumn(
            name = "product_id",
            nullable = false
    )
    private Product product;

    public LineItem() {
    }

    public LineItem(Integer quantity,
                    Double subTotal,
                    Double selledPrice,
                    Order order,
                    Product product) {
        this.quantity = quantity;
        this.subTotal = subTotal;
        this.selledPrice = selledPrice;
        this.order = order;
        this.product = product;
    }

    public LineItem(Long id, Integer quantity, Double subTotal, Order order, Product product) {
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

    public Double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(Double subTotal) {
        this.subTotal = subTotal;
    }

    public Double getSelledPrice() {
        return selledPrice;
    }

    public void setSelledPrice(Double selledPrice) {
        this.selledPrice = selledPrice;
    }
}
