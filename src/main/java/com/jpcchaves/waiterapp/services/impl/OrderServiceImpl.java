package com.jpcchaves.waiterapp.services.impl;

import com.jpcchaves.waiterapp.entities.LineItem;
import com.jpcchaves.waiterapp.entities.Order;
import com.jpcchaves.waiterapp.entities.Product;
import com.jpcchaves.waiterapp.exceptions.ResourceNotFoundException;
import com.jpcchaves.waiterapp.payload.dtos.lineitem.LineItemDataDto;
import com.jpcchaves.waiterapp.payload.dtos.order.OrderRequestDto;
import com.jpcchaves.waiterapp.payload.dtos.order.OrderResponseDto;
import com.jpcchaves.waiterapp.repositories.LineItemRepository;
import com.jpcchaves.waiterapp.repositories.OrderRepository;
import com.jpcchaves.waiterapp.repositories.ProductRepository;
import com.jpcchaves.waiterapp.services.OrderService;
import com.jpcchaves.waiterapp.utils.mapper.MapperUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final LineItemRepository lineItemRepository;
    private final MapperUtils mapper;

    public OrderServiceImpl(OrderRepository orderRepository,
                            ProductRepository productRepository,
                            LineItemRepository lineItemRepository,
                            MapperUtils mapper) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.lineItemRepository = lineItemRepository;
        this.mapper = mapper;
    }

    @Override
    public List<OrderResponseDto> getAll() {
        List<Order> orders = orderRepository.findAll();
        List<OrderResponseDto> ordersDto = mapper
                .parseListObjects(orders, OrderResponseDto.class);

        return ordersDto;
    }

    @Override
    public OrderResponseDto create(OrderRequestDto orderRequestDto) {
        OrderResponseDto orderResponseDto = new OrderResponseDto();
        orderResponseDto.setOrderDetails(orderRequestDto.getOrderDetails());

        Order newOrder = orderRepository.save(mapper.parseObject(orderResponseDto, Order.class));

        List<LineItemDataDto> items = orderRequestDto.getLineItems();
        List<LineItem> itemsToSave = new ArrayList<>();

        for (LineItemDataDto item : items) {
            Product product = productRepository
                    .findById(item.getProductId())
                    .orElseThrow(() -> new ResourceNotFoundException("Product not found for the given id " + item.getProductId()));

            Double subTotal = calculateSubTotal(item.getQuantity(), product.getPrice());

            LineItem lineItem = new LineItem(item.getQuantity(), subTotal, newOrder, product);
            itemsToSave.add(lineItem);
            newOrder.getLineItems().add(lineItem);
        }

        lineItemRepository.saveAll(itemsToSave);
        Order savedOrder = orderRepository.save(newOrder);

        return mapper.parseObject(savedOrder, OrderResponseDto.class);
    }

    private Double calculateSubTotal(Integer quantity, Double price) {
        return quantity * price;
    }
}
