package com.jpcchaves.waiterapp.services;

import com.jpcchaves.waiterapp.payload.dtos.lineitem.LineItemAddedDto;
import com.jpcchaves.waiterapp.payload.dtos.lineitem.LineItemDataDto;

public interface LineItemService {
    LineItemAddedDto createLineItem(LineItemDataDto lineItem);
}
