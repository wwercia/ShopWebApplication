package com.werka.shopwebapplication.domain.basket;

import com.werka.shopwebapplication.domain.book.Book;
import com.werka.shopwebapplication.domain.client.Client;
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
        final String sql = "INSERT INTO basket (client_id, book_id) VALUES (?, ?)";
        try(Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            statement.setString(1, ""+basket.getClientId());
            statement.setString(2, ""+basket.getBookId());
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
