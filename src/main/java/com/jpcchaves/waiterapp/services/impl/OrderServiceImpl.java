package com.jpcchaves.waiterapp.services.impl;

import com.jpcchaves.waiterapp.Enum.OrderStatus;
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
import com.jpcchaves.waiterapp.utils.ordercalcs.OrderCalcs;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;


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
        orderResponseDto.setOrderCode(new UUID(1, 20));
        orderResponseDto.setStatus(OrderStatus.WAITING);
        orderResponseDto.setDone(false);
        orderResponseDto.setPaid(false);

        Order newOrder = orderRepository.save(mapper.parseObject(orderResponseDto, Order.class));

        List<LineItemDataDto> items = orderRequestDto.getLineItems();
        List<LineItem> itemsToSave = new ArrayList<>();

        for (LineItemDataDto item : items) {
            Product product = productRepository
                    .findById(item.getProductId())
                    .orElseThrow(() -> new ResourceNotFoundException("Product not found for the given id " + item.getProductId()));

            Double subTotal = OrderCalcs.calculateSubTotal(item.getQuantity(), product.getPrice());

            LineItem lineItem = new LineItem(item.getQuantity(), subTotal, newOrder, product);
            itemsToSave.add(lineItem);

            newOrder.getLineItems().add(lineItem);
        }

        Double orderTotal = OrderCalcs.calculateOrderTotal(new HashSet<>(itemsToSave));

        newOrder.setOrderTotal(orderTotal);

        lineItemRepository.saveAll(itemsToSave);
        Order savedOrder = orderRepository.save(newOrder);

        return mapper.parseObject(savedOrder, OrderResponseDto.class);
    }

    @Override
    public OrderResponseDto getById(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No such order found for id " + id));
        OrderResponseDto orderResponseDto = mapper.parseObject(order, OrderResponseDto.class);
        return orderResponseDto;
    }
}
