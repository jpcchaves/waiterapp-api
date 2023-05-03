package com.jpcchaves.waiterapp.controllers;

import com.jpcchaves.waiterapp.payload.dtos.order.OrderRequestDto;
import com.jpcchaves.waiterapp.payload.dtos.order.OrderResponseDto;
import com.jpcchaves.waiterapp.services.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<OrderResponseDto>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @PostMapping
    public ResponseEntity<OrderResponseDto> create(@RequestBody OrderRequestDto orderRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(orderRequestDto));
    }

}
