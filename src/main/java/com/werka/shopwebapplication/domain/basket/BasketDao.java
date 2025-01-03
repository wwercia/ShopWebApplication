package com.werka.shopwebapplication.domain.basket;

import com.werka.shopwebapplication.config.DataHelper;
import com.werka.shopwebapplication.domain.api.*;
import com.werka.shopwebapplication.domain.book.Book;
import com.werka.shopwebapplication.domain.book.BookDao;
import com.werka.shopwebapplication.domain.common.BaseDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BasketDao extends BaseDao {


    public List<Basket> findAll() {
        final String sql = "SELECT * FROM basket";
        List<Basket> resultList = new ArrayList<>();
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int clientId = resultSet.getInt("client_id");
                int bookId = resultSet.getInt("book_id");
                resultList.add(new Basket(id, clientId, bookId));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultList;
    }

    public void saveBook(Basket basket) {
        List<BasketBooksInfo> list = getBooksInBasket(DataHelper.getClientId());
        int quantity = 0;
        for(BasketBooksInfo book : list) {
            if(book.getBookId() == basket.getBookId()){
                quantity = book.getQuantity();
            }
        }
        quantity++;
        if(quantity > 1){
            try(Connection connection = getConnection();
                Statement statement = connection.createStatement()){
                final String sql = "UPDATE basket SET quantity = '" + quantity + "' WHERE (`book_id` = '" + basket.getBookId() +"');";
                int updatedRows = statement.executeUpdate(sql);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }else {
            //nie ma jeszcze w koszyku
            final String sql = "INSERT INTO basket (client_id, book_id, quantity) VALUES (?, ?, ?)";
            try(Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
                statement.setString(1, ""+basket.getClientId());
                statement.setString(2, ""+basket.getBookId());
                statement.setString(3, "1");
                statement.executeUpdate();
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if(generatedKeys.next()) {
                    basket.setId(generatedKeys.getInt(1));
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public List<BasketBooksInfo> getBooksInBasket(int clientId) {
        final String sql = "SELECT * FROM basket WHERE client_id='" + clientId + "'";
        List<BasketBooksInfo> list = new ArrayList<>();
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int clientIdd = resultSet.getInt("client_id");
                int bookId = resultSet.getInt("book_id");
                int quantity = resultSet.getInt("quantity");
                list.add(new BasketBooksInfo(id, clientIdd, bookId, quantity));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public void updateBookQuantity(String title, int quantity, List<BasicBasketBookInfo> booksInBasket) {

        BasicBasketBookInfo book = null;
        for(BasicBasketBookInfo bookk : booksInBasket) {
            if(bookk.getTitle().equals(title)){
                book = bookk;
            }
        }


        if(quantity == 0) {
            int id = getId(book.getId());
            try(Connection connection = getConnection();
                Statement statement = connection.createStatement()){
                final String sql = "DELETE FROM basket WHERE (`id` = '" + id + "');";
                statement.executeUpdate(sql);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return;
        }

        if(book != null) {
            try(Connection connection = getConnection();
                Statement statement = connection.createStatement()){
                final String sql = "UPDATE basket SET quantity = '" + quantity + "' WHERE (`book_id` = '" + book.getId() +"');";
                int updatedRows = statement.executeUpdate(sql);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }

    private int getId(int bookId) {
        List<BasketBooksInfo> books = getBooksInBasket(DataHelper.getClientId());
        for(BasketBooksInfo book : books) {
            if(book.getBookId() == bookId){
                return book.getId();
            }
        }
        return -1;
    }

    public void removeBookFromBasket(int bookId) {
        final String sql = "DELETE FROM basket WHERE book_id = ?";
        try(Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, bookId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
