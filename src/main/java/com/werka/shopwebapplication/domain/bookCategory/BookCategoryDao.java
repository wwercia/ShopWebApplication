package com.werka.shopwebapplication.domain.bookCategory;

import com.werka.shopwebapplication.domain.common.BaseDao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BookCategoryDao extends BaseDao {

    public List<BookCategory> findAll() {
        final String sql = "SELECT * FROM book_category";
        return getBookCategories(sql);
    }

    private List<BookCategory> getBookCategories(String sql) {
        List<BookCategory> resultList = new ArrayList<>();
        try (Connection connection = getConnection();
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
