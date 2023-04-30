package com.jpcchaves.waiterapp.services;

import com.jpcchaves.waiterapp.entities.Order;

import java.util.List;

public interface OrderService {
    List<Order> getAll();
}
