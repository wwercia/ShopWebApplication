package com.werka.shopwebapplication.products.dao;

import java.math.BigDecimal;
import java.util.Date;

public class Book {

    private int id;
    private String title;
    private String author;
    private String description;
    private BigDecimal price;
    private String publisher;
    private float rating;
    private String series;
    private int pages;
    private String cover;
    private Date publicationDate;

    public Book(String title, String author, String description, BigDecimal price, String publisher, float rating, String series, int pages, String cover, Date publicationDate) {
        this.author = author;
        this.title = title;
        this.description = description;
        this.price = price;
        this.publisher = publisher;
        this.rating = rating;
        this.series = series;
        this.pages = pages;
        this.cover = cover;
        this.publicationDate = publicationDate;
    }

    public Book(int id, String title,  String author, String description, BigDecimal  price, String publisher, float rating, String series, int pages, String cover, Date publicationDate) {
        this(title, author, description, price, publisher, rating, series, pages, cover, publicationDate);
        this.id = id;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal  getPrice() {
        return price;
    }

    public void setPrice(BigDecimal  price) {
        this.price = price;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    @Override
    public String toString() {
        return String.format("title: %s, author: %s, price: %f", title, author, price);
    }
}
