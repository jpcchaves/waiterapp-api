package com.jpcchaves.waiterapp.services;

import com.jpcchaves.waiterapp.payload.dtos.lineitem.LineItemAddedDto;
import com.jpcchaves.waiterapp.payload.dtos.lineitem.LineItemDto;

public interface LineItemService {
    LineItemAddedDto createLineItem(LineItemDto lineItem);
}
