package com.werka.shopwebapplication.domain.bookCategory;

public class BookCategory {

    int id;
    int bookId;
    int categoryId;

    public BookCategory(int bookId, int categoryId) {
        this.bookId = bookId;
        this.categoryId = categoryId;
    }

    public BookCategory(int id, int bookId, int categoryId) {
        this(bookId, categoryId);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public String toString() {
        return "book id: " + bookId + ", category id: " + categoryId;
    }
}
