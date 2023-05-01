package com.jpcchaves.waiterapp.services.impl;

import com.jpcchaves.waiterapp.entities.Order;
import com.jpcchaves.waiterapp.payload.dtos.order.OrderResponseDto;
import com.jpcchaves.waiterapp.repositories.OrderRepository;
import com.jpcchaves.waiterapp.services.OrderService;
import com.jpcchaves.waiterapp.utils.mapper.MapperUtils;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final MapperUtils mapper;

    public OrderServiceImpl(OrderRepository orderRepository,
                            MapperUtils mapper) {
        this.orderRepository = orderRepository;
        this.mapper = mapper;
    }

    @Override
    public List<OrderResponseDto> getAll() {
        List<Order> orders = orderRepository.findAll();
        List<OrderResponseDto> ordersDto = mapper
                .parseListObjects(orders, OrderResponseDto.class);

        return ordersDto;
    }
}
