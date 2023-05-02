package com.jpcchaves.waiterapp.services.impl;

import com.jpcchaves.waiterapp.entities.LineItem;
import com.jpcchaves.waiterapp.entities.Order;
import com.jpcchaves.waiterapp.entities.Product;
import com.jpcchaves.waiterapp.exceptions.BadRequestException;
import com.jpcchaves.waiterapp.exceptions.ResourceNotFoundException;
import com.jpcchaves.waiterapp.payload.dtos.lineitem.LineItemDto;
import com.jpcchaves.waiterapp.repositories.LineItemRepository;
import com.jpcchaves.waiterapp.repositories.OrderRepository;
import com.jpcchaves.waiterapp.repositories.ProductRepository;
import com.jpcchaves.waiterapp.services.LineItemService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class LineItemServiceImpl implements LineItemService {

    private final LineItemRepository repository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    public LineItemServiceImpl(LineItemRepository repository,
                               ProductRepository productRepository,
                               OrderRepository orderRepository) {
        this.repository = repository;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public String createLineItem(LineItemDto lineItem) {
        Order order = orderRepository
                .findById(lineItem.getOrderId())
                .orElseThrow(() -> new ResourceNotFoundException("Order not found for the given id: " + lineItem.getOrderId()));

        Product product = productRepository
                .findById(lineItem.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException("Product not found for the given id: " + lineItem.getProductId()));

        if (hasDuplicateItem(order.getLineItems(), product)) {
            throw new BadRequestException("The product has already been added: " + product.getName() + ". Instead of adding, try editing the product");
        }

        LineItem newItem = new LineItem();

        newItem.setSubTotal(calculateSubTotal(lineItem.getQuantity(), product.getPrice()));
        newItem.setOrder(order);
        newItem.setProduct(product);
        newItem.setQuantity(lineItem.getQuantity());

        order.getLineItems().add(newItem);

        Double total = calculateOrderTotal(order.getLineItems());
        order.setOrderTotal(total);

        repository.save(newItem);
        return "Item successfully added to the order";
    }

    private Double calculateSubTotal(Integer quantity, Double price) {
        return quantity * price;
    }

    private Double calculateOrderTotal(Set<LineItem> lineItems) {
        Double total = 0.0;
        for (LineItem lineItem : lineItems) {
            total += lineItem.getSubTotal();
        }
        return total;
    }

    private Boolean hasDuplicateItem(Set<LineItem> lineItems, Product product) {
        for (LineItem item : lineItems) {
            if (item.getProduct().equals(product)) {
                return true;
            }
        }
        return false;
    }
}
