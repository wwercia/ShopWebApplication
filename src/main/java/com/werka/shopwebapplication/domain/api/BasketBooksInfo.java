package com.werka.shopwebapplication.domain.api;

public class BasketBooksInfo {

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    private int id;
    private int clientId;
    private int bookId;
    private int quantity;

    public BasketBooksInfo(int id, int clientId, int bookId, int quantity) {
        this.id = id;
        this.clientId = clientId;
        this.bookId = bookId;
        this.quantity = quantity;
    }
}
