package com.jpcchaves.waiterapp.services.impl;

import com.jpcchaves.waiterapp.entities.LineItem;
import com.jpcchaves.waiterapp.entities.Order;
import com.jpcchaves.waiterapp.entities.Product;
import com.jpcchaves.waiterapp.exceptions.BadRequestException;
import com.jpcchaves.waiterapp.exceptions.ResourceNotFoundException;
import com.jpcchaves.waiterapp.payload.dtos.ApiMessageResponseDto;
import com.jpcchaves.waiterapp.payload.dtos.lineitem.LineItemAddedDto;
import com.jpcchaves.waiterapp.payload.dtos.lineitem.LineItemDataDto;
import com.jpcchaves.waiterapp.repositories.LineItemRepository;
import com.jpcchaves.waiterapp.repositories.OrderRepository;
import com.jpcchaves.waiterapp.repositories.ProductRepository;
import com.jpcchaves.waiterapp.services.LineItemService;
import com.jpcchaves.waiterapp.utils.ordercalcs.OrderCalcs;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class LineItemServiceImpl implements LineItemService {

    private final LineItemRepository repository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final LineItemRepository lineItemRepository;

    public LineItemServiceImpl(LineItemRepository repository,
                               ProductRepository productRepository,
                               OrderRepository orderRepository,
                               LineItemRepository lineItemRepository) {
        this.repository = repository;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
        this.lineItemRepository = lineItemRepository;
    }

    @Override
    public LineItemAddedDto createLineItem(LineItemDataDto lineItem) {
        Order order = orderRepository
                .findById(lineItem.getOrderId())
                .orElseThrow(() -> new ResourceNotFoundException("Order not found for the given id: " + lineItem.getOrderId()));

        Product product = productRepository
                .findById(lineItem.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException("Product not found for the given id: " + lineItem.getProductId()));

        if (hasProductInOrder(order.getLineItems(), product)) {
            throw new BadRequestException("The product has already been added: " + product.getName() + ". Instead of adding, try editing the product");
        }

        LineItem newItem = new LineItem();


        newItem.setSelledPrice(product.getPrice());
        newItem.setSubTotal(OrderCalcs.calculateSubTotal(lineItem.getQuantity(), newItem.getSelledPrice()));
        newItem.setOrder(order);
        newItem.setProduct(product);
        newItem.setQuantity(lineItem.getQuantity());

        order.getLineItems().add(newItem);

        Double total = OrderCalcs.calculateOrderTotal(order.getLineItems());
        order.setOrderTotal(total);

        repository.save(newItem);

        return new LineItemAddedDto("Item successfully added to the order");
    }

    @Override
    public ApiMessageResponseDto removeLineItem(LineItemDataDto lineItem) {
        Order order = orderRepository
                .findById(lineItem.getOrderId())
                .orElseThrow(() -> new ResourceNotFoundException("Order not found for the given id: " + lineItem.getOrderId()));

        Product product = productRepository
                .findById(lineItem.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException("Product not found for the given id: " + lineItem.getProductId()));

        if (!hasProductInOrder(order.getLineItems(), product)) {
            throw new BadRequestException("The product doesn't exist in the order: " + product.getName());
        }


        for (LineItem item : order.getLineItems()) {
            if (item.getProduct().equals(product)) {
                order.getLineItems().remove(item);
                lineItemRepository.deleteById(item.getId());
            }
        }

        Double total = OrderCalcs.calculateOrderTotal(order.getLineItems());
        order.setOrderTotal(total);

        orderRepository.save(order);

        return new ApiMessageResponseDto("Item removed successfully");
    }

    private Boolean hasProductInOrder(Set<LineItem> lineItems, Product product) {
        for (LineItem item : lineItems) {
            if (item.getProduct().equals(product)) {
                return true;
            }
        }
        return false;
    }
}
