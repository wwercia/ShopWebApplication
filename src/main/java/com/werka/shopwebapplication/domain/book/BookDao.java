package com.werka.shopwebapplication.domain.book;

import com.werka.shopwebapplication.domain.api.BookFullInfo;
import com.werka.shopwebapplication.domain.common.BaseDao;

import java.math.BigDecimal;
import java.sql.*;
import java.util.*;
import java.util.Date;

public class BookDao extends BaseDao {

    private List<Book> allBooks = null;

    public List<Book> findAll() {
        if(allBooks == null) {
            final String sql = "SELECT * FROM books";
            List<Book> resultList = new ArrayList<>();
            try (Connection connection = getConnection();
                 Statement statement = connection.createStatement()) {
                ResultSet resultSet = statement.executeQuery(sql);
                while (resultSet.next()) {
                    resultList.add(getBookFromResultSet(resultSet));
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            allBooks = resultList;
            return resultList;
        }else {
            return allBooks;
        }
    }

    public List<Book> getBestRatedBooks(int number) {
        if (allBooks == null) {
            findAll();
        }
        List<Book> sortedBooks = new ArrayList<>(allBooks);
        Collections.sort(sortedBooks, new Comparator<Book>() {
            @Override
            public int compare(Book b1, Book b2) {
                return Double.compare(b2.getRating(), b1.getRating());
            }
        });
        List<Book> result = new ArrayList<>();
        for (int i = 0; i < Math.min(number, sortedBooks.size()); i++) {
            result.add(sortedBooks.get(i));
        }
        return result;
    }

    public List<Book> getRecommendedBooks() {

        List<String> titles = new ArrayList<>();
        titles.add("Niewidzialne życie Addie LaRue");
        titles.add("Flaw(less)");
        titles.add("Rodzina Monet");
        titles.add("shatter me");

        List<Book> result = new ArrayList<>();
        for(String title : titles){
            final String sql = "SELECT * FROM books WHERE title = ?";
            try (Connection connection = getConnection();
                 PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, title);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    result.add(getBookFromResultSet(resultSet));
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return result;
    }

    public Optional<Book> findBookById(int bookId) {

        final String sql = "SELECT * FROM books WHERE id = ?";
        Book result = null;
        try (Connection connection = getConnection();
             PreparedStatement  statement = connection.prepareStatement(sql)) {
            statement.setString(1, bookId + "");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                result = getBookFromResultSet(resultSet);
            }
            return Optional.of(result);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<Book> findBookByTitle(String titlee) {
        final String sql = "SELECT * FROM books WHERE title = ?";
        Book result = null;
        try (Connection connection = getConnection();
             PreparedStatement  statement = connection.prepareStatement(sql)) {
            statement.setString(1, titlee);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                result = getBookFromResultSet(resultSet);
            }
            return Optional.of(result);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Book getBookFromResultSet(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String title = resultSet.getString("title");
        String author = resultSet.getString("author");
        String description = resultSet.getString("description");
        BigDecimal price = resultSet.getBigDecimal("price");
        String publisher = resultSet.getString("publisher");
        float rating = resultSet.getFloat("rating");
        String series = resultSet.getString("series");
        int pages = resultSet.getInt("pages");
        String cover = resultSet.getString("cover");
        Date publicationDate = resultSet.getDate("publication-date");
        return new Book(id, title, author, description, price, publisher, rating, series, pages, cover, publicationDate);
    }

}
