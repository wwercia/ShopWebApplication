package com.werka.shopwebapplication.domain;

public class CurrentOrderId {

    private static int orderId;

    public static int getOrderId() {
        return orderId;
    }

    public static void setOrderId(int orderIdd) {
        orderId = orderIdd;
    }

}
