package com.jpcchaves.waiterapp.payload.dtos.lineitem;

public class LineItemAddedDto {
    private String message;

    public LineItemAddedDto() {
    }

    public LineItemAddedDto(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
