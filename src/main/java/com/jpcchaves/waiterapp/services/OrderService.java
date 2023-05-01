package com.jpcchaves.waiterapp.services;

import com.jpcchaves.waiterapp.payload.dtos.order.OrderResponseDto;

import java.util.List;

public interface OrderService {
    List<OrderResponseDto> getAll();
}
