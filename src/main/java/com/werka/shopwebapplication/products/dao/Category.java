package com.werka.shopwebapplication.products.dao;

public class Category {

    private int id;
    private String category;

    Category(String category) {
        this.category = category;
    }
    Category(int id, String category) {
        this(category);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return category;
    }
}
