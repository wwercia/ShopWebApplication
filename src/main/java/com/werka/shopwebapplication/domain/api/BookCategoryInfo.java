package com.werka.shopwebapplication.domain.api;

public class BookCategoryInfo {

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    private Integer id;
    private Integer bookId;
    private Integer categoryId;

    public BookCategoryInfo(Integer id, Integer bookId, Integer categoryId) {
        this.id = id;
        this.bookId = bookId;
        this.categoryId = categoryId;
    }


}
