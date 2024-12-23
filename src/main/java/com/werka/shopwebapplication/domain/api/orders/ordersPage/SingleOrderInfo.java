package com.werka.shopwebapplication.domain.api.orders.ordersPage;

import java.util.List;

public class SingleOrderInfo {

    private int orderId;
    private List<OrderedBook> orderedBooks;
    private double total;

    public SingleOrderInfo(int orderId, List<OrderedBook> orderedBooks, double total) {
        this.orderId = orderId;
        this.orderedBooks = orderedBooks;
        this.total= total;
    }

    public void addOrderedBook(OrderedBook orderedBook) {
        orderedBooks.add(orderedBook);
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public List<OrderedBook> getOrderedBooks() {
        return orderedBooks;
    }

    public void setOrderedBooks(List<OrderedBook> orderedBooks) {
        this.orderedBooks = orderedBooks;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
