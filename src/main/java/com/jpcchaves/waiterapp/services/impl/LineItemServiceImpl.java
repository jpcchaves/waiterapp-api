package com.jpcchaves.waiterapp.services.impl;

import com.jpcchaves.waiterapp.entities.LineItem;
import com.jpcchaves.waiterapp.payload.dtos.lineitem.LineItemDto;
import com.jpcchaves.waiterapp.entities.Order;
import com.jpcchaves.waiterapp.entities.Product;
import com.jpcchaves.waiterapp.repositories.LineItemRepository;
import com.jpcchaves.waiterapp.repositories.OrderRepository;
import com.jpcchaves.waiterapp.repositories.ProductRepository;
import com.jpcchaves.waiterapp.services.LineItemService;
import org.springframework.stereotype.Service;

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
        boolean hasOrder = orderRepository.existsById(lineItem.getOrderId());
        boolean hasProduct = productRepository.existsById(lineItem.getProductId());

        if(!hasOrder){
            throw new RuntimeException("Order not found for the given id: " + lineItem.getOrderId());
        };

        if (!hasProduct) {
            throw new RuntimeException("Product not found for the given id: " + lineItem.getProductId());
        }

        Optional<Order> order = orderRepository.findById(lineItem.getOrderId());
        Optional<Product> product = productRepository.findById(lineItem.getProductId());

        LineItem newItem = new LineItem();

        if (order.isPresent() && product.isPresent()) {
            newItem.setSubTotal(calculateSubTotal(lineItem.getQuantity(), product.get().getPrice()));
            newItem.setOrder(order.get());
            newItem.setProduct(product.get());
            newItem.setQuantity(lineItem.getQuantity());
        }

        repository.save(newItem);
        return "Item successfully added to the order";
    }

    private Double calculateSubTotal(Integer quantity, Double price) {
        return quantity * price;
    }
}
