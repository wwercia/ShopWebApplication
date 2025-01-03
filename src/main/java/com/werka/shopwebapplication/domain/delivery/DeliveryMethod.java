package com.werka.shopwebapplication.domain.delivery;

public enum DeliveryMethod {
    STANDARD("standard", 10, "2-3 business days"),
    EXPRESS("express", 15, "Next day"),
    PICKUP("pickup", 0, "Ready in 2 hours");

    private final String displayName;
    private final double price;
    private final String deliveryTime;

    DeliveryMethod(String displayName, double price, String deliveryTime) {
        this.displayName = displayName;
        this.price = price;
        this.deliveryTime = deliveryTime;
    }

    public String getDisplayName() {
        return displayName;
    }

    public double getPrice() {
        return price;
    }

    public String getDeliveryTime() {
        return deliveryTime;
    }
}
