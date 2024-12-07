package com.werka.shopwebapplication.domain.orders;

import com.werka.shopwebapplication.config.DataHelper;
import com.werka.shopwebapplication.domain.CurrentOrderId;
import com.werka.shopwebapplication.domain.basket.Basket;
import com.werka.shopwebapplication.domain.book.Book;
import com.werka.shopwebapplication.domain.common.BaseDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderDao extends BaseDao {

    public List<Basket> findAll() {
        final String sql = "SELECT * FROM orders";
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

    public int getNewOrderId() {

        final String sql = "SELECT MAX(`order_id`) AS `maxOrderId` FROM orders";
        int result = -1;
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                result = resultSet.getInt("maxOrderId") + 1; // wieksze o 1 niz najwieksze w bazie
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("nowe id: " + result);
        return result;
    }

    public void saveOrder(int bookId) {
        final String sql = "INSERT INTO orders (book_id, client_id, order_id) VALUES (?, ?, ?)";
        try(Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, bookId);
            statement.setInt(2, DataHelper.getClientId());
            statement.setInt(3, CurrentOrderId.getOrderId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
