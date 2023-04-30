package com.jpcchaves.waiterapp.services;

import com.jpcchaves.waiterapp.payload.dtos.lineitem.LineItemDto;

public interface LineItemService {
    String createLineItem(LineItemDto lineItem);
}
