package com.jpcchaves.waiterapp.controllers;

import com.jpcchaves.waiterapp.payload.dtos.lineitem.LineItemDto;
import com.jpcchaves.waiterapp.services.LineItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/lineitems")
public class LineItemController {
    private final LineItemService service;

    public LineItemController(LineItemService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<String> create (@RequestBody LineItemDto lineItemDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createLineItem(lineItemDto));
    }
}
