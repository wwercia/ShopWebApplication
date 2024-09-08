package com.werka.shopwebapplication.domain.api;

import java.math.BigDecimal;
import java.util.Date;

public class BookBasicInfo {

    private int id;
    private String title;
    private String author;
    private BigDecimal price;
    private float rating;

    public BookBasicInfo(int id, String title, String author, BigDecimal price, float rating) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.price = price;
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

}
