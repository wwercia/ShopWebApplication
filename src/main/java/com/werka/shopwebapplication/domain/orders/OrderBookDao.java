package com.werka.shopwebapplication.domain.orders;

import com.werka.shopwebapplication.domain.CurrentOrderId;
import com.werka.shopwebapplication.domain.common.BaseDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderBookDao extends BaseDao {

    public List<Order> findAll() {
        final String sql = "SELECT * FROM orders";
        List<Order> resultList = new ArrayList<>();
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int bookId = resultSet.getInt("book_id");
                int clientId = resultSet.getInt("client_id");
                int orderId = resultSet.getInt("order_id");
                int quantity = resultSet.getInt("quantity");
                resultList.add(new Order(id, bookId, clientId, orderId, quantity));
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
        return result;
    }

    public void saveOrder(int bookId, int quantity, int clientId) {
        final String sql = "INSERT INTO orders (book_id, client_id, order_id, quantity) VALUES (?, ?, ?, ?)";
        try(Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, bookId);
            statement.setInt(2, clientId);
            statement.setInt(3, CurrentOrderId.getOrderId());
            statement.setInt(4, quantity);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Integer> getOrderIds() {
        final String sql = "SELECT DISTINCT order_id FROM orders";
        List<Integer> resultList = new ArrayList<>();
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int id = resultSet.getInt("order_id");
                resultList.add(id);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultList;
    }

}
