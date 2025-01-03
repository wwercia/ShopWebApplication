package com.werka.shopwebapplication.domain.orders;

public class Order {

    private int id;
    private int bookId;
    private int clientId;
    private int orderId;
    private int quantity;

    public Order(int bookId, int clientId, int orderId, int quantity) {
        this.clientId = clientId;
        this.bookId = bookId;
        this.orderId = orderId;
        this.quantity = quantity;
    }

    public Order(int id, int bookId, int clientId, int orderId, int quantity){
        this(bookId, clientId, orderId, quantity);
        this.id = id;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
