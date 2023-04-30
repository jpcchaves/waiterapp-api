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
        boolean hasOrder = orderRepository.existsById(lineItem.getOrderId());
        boolean hasProduct = productRepository.existsById(lineItem.getProductId());

        if(!hasOrder){
            throw new RuntimeException("Order not found for the given id: " + lineItem.getOrderId());
        };

        if (!hasProduct) {
            throw new RuntimeException("Product not found for the given id: " + lineItem.getProductId());
        }

        Optional<Order> optionalOrder = orderRepository.findById(lineItem.getOrderId());
        Optional<Product> optionalProduct = productRepository.findById(lineItem.getProductId());

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
