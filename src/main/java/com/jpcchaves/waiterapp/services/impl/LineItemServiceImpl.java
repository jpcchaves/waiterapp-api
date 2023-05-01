package com.jpcchaves.waiterapp.services.impl;

import com.jpcchaves.waiterapp.entities.LineItem;
import com.jpcchaves.waiterapp.payload.dtos.lineitem.LineItemDto;
import com.jpcchaves.waiterapp.entities.Order;
import com.jpcchaves.waiterapp.entities.Product;
import com.jpcchaves.waiterapp.repositories.LineItemRepository;
import com.jpcchaves.waiterapp.repositories.OrderRepository;
import com.jpcchaves.waiterapp.repositories.ProductRepository;
import com.jpcchaves.waiterapp.services.LineItemService;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        Optional<Order> optionalOrder = Optional.ofNullable(orderRepository
                .findById(lineItem.getOrderId())
                .orElseThrow(() -> new RuntimeException("Order not found for the given id: " + lineItem.getOrderId())));

        Optional<Product> optionalProduct = Optional.ofNullable(productRepository
                .findById(lineItem.getProductId())
                .orElseThrow(() -> new RuntimeException("Order not found for the given id: " + lineItem.getOrderId())));

        LineItem newItem = new LineItem();

        if (optionalOrder.isPresent() && optionalProduct.isPresent()) {
            Order order = optionalOrder.get();
            Product product = optionalProduct.get();
            newItem.setSubTotal(calculateSubTotal(lineItem.getQuantity(), product.getPrice()));
            newItem.setOrder(order);
            newItem.setProduct(product);
            newItem.setQuantity(lineItem.getQuantity());

            Double total = calculateOrderTotal(order.getLineItems());
            order.setOrderTotal(total);
        }

        repository.save(newItem);
        return "Item successfully added to the order";
    }

    private Double calculateSubTotal(Integer quantity, Double price) {
        return quantity * price;
    }

    private Double calculateOrderTotal(List<LineItem> lineItems) {
        Double total = 0.0;
        for (LineItem lineItem : lineItems) {
            total += lineItem.getSubTotal();
        }

        return total;
    }
}
