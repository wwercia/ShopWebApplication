package com.werka.shopwebapplication.start;

import com.werka.shopwebapplication.servlets.DataSourceProvider;

import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;

public class ClientDAO {

    private final DataSource dataSource;

    public ClientDAO() {
        try {
            this.dataSource = DataSourceProvider.getDataSource();
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
    }

    public void save(Client client) {
        final String sql = "INSERT INTO clients (email, password, first_name, surname) VALUES (?, ?, ?, ?)";
        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            statement.setString(1, client.getEmail());
            statement.setString(2, client.getPassword());
            statement.setString(3, client.getName());
            statement.setString(4, client.getSurname());
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if(generatedKeys.next()) {
                client.setId(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isFoundClientByMailAndPass(String email, String password){
        final String sql = String.format("SELECT first_name FROM clients WHERE email='%s' AND password='%s'", email, password);
        try(Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public boolean isMailFree(String email){
        final String sql = String.format("SELECT first_name FROM clients WHERE email='%s'", email);
        try(Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

}
