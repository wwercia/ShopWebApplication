package com.werka.shopwebapplication.basket;

import com.werka.shopwebapplication.config.DataSourceProvider;
import com.werka.shopwebapplication.products.dao.Category;

import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BasketDao {

    private final DataSource dataSource;

    BasketDao() {
        try {
            this.dataSource = DataSourceProvider.getDataSource();
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
    }

    List<Basket> findAll() {
        final String sql = "SELECT * FROM basket";
        List<Basket> resultList = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
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
