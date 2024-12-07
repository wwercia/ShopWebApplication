package com.werka.shopwebapplication.domain.api;

import com.werka.shopwebapplication.domain.delivery.DeliveryMethod;

import java.util.List;

public class OrderFullInfo {

    private List<BasicBasketBookInfo> books;
    private double totalPrice;
    private DeliveryMethod deliveryMethod;

    public OrderFullInfo(List<BasicBasketBookInfo> books, double totalPrice, DeliveryMethod deliveryMethod) {
        this.books = books;
        this.totalPrice = totalPrice;
        this.deliveryMethod = deliveryMethod;
    }

    public List<BasicBasketBookInfo> getBooks() {
        return books;
    }

    public void setBooks(List<BasicBasketBookInfo> books) {
        this.books = books;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public DeliveryMethod getDeliveryMethod() {
        return deliveryMethod;
    }

    public void setDeliveryMethod(DeliveryMethod deliveryMethod) {
        this.deliveryMethod = deliveryMethod;
    }
}
