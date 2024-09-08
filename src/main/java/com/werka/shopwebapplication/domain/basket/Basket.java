package com.werka.shopwebapplication.domain.basket;

public class Basket {

    private int id;
    private int clientId;
    private int bookId;

    public Basket(int clientId, int bookId) {
        this.clientId = clientId;
        this.bookId = bookId;
    }

    public Basket(int id, int clientId, int bookId){
        this(clientId, bookId);
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

}
