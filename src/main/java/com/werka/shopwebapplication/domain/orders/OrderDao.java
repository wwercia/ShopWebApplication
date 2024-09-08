package com.werka.shopwebapplication.domain.orders;

import com.werka.shopwebapplication.domain.basket.Basket;
import com.werka.shopwebapplication.domain.common.BaseDao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OrderDao extends BaseDao {

    List<Basket> findAll() {
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

}
