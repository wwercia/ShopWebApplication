package com.werka.shopwebapplication.domain.api.orders;

public class OrderBookInfo {

    private int id;
    private int bookId;
    private int clientId;
    private int orderId;

    public OrderBookInfo(int id, int bookId, int clientId, int orderId) {
        this.id = id;
        this.clientId = clientId;
        this.bookId = bookId;
        this.orderId = orderId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

}
