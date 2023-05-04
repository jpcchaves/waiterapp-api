package com.jpcchaves.waiterapp.Enum;

public enum OrderStatus {
    WAITING(0),
    IN_PROGRESS(1),
    DONE(2);

    private final int code;

    OrderStatus(int code) {
        this.code = code;
    }

    public static OrderStatus valueOf(int code) {
        for (OrderStatus value : OrderStatus.values()) {
            if (code == value.getCode()) {
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid code: " + code);
    }

    public int getCode() {
        return code;
    }

}
