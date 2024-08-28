package com.werka.shopwebapplication.products.dao;

import com.werka.shopwebapplication.config.DataSourceProvider;

import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BookCategoryDao {

    private final DataSource dataSource;

    public BookCategoryDao() {
        try {
            this.dataSource = DataSourceProvider.getDataSource();
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
    }

    public List<BookCategory> findAll() {
        final String sql = "SELECT * FROM book_category";
        return getBookCategories(sql);
    }

    private List<BookCategory> getBookCategories(String sql) {
        List<BookCategory> resultList = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int bookId = resultSet.getInt("book_id");
                int categoryId = resultSet.getInt("category_id");
                resultList.add(new BookCategory(id, bookId, categoryId));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultList;
    }

}
