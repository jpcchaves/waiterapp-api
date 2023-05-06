package com.jpcchaves.waiterapp.Enum;

public enum ProductStatus {
    ACTIVE(0),
    INACTIVE(1);

    private final int code;

    ProductStatus(int code) {
        this.code = code;
    }

    public static ProductStatus valueOf(int code) {
        for (ProductStatus value : ProductStatus.values()) {
            if (code == value.getCode()) {
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid order status: " + code);
    }

    public int getCode() {
        return code;
    }
}
