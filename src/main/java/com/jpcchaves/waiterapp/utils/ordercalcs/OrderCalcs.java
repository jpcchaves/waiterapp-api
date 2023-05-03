package com.jpcchaves.waiterapp.utils.ordercalcs;

import com.jpcchaves.waiterapp.entities.LineItem;

import java.util.Set;

public class OrderCalcs {

    public static Double calculateSubTotal(Integer quantity, Double price) {
        return quantity * price;
    }

    public static Double calculateOrderTotal(Set<LineItem> lineItems) {
        Double total = 0.0;
        for (LineItem lineItem : lineItems) {
            total += lineItem.getSubTotal();
        }
        return total;
    }
}
