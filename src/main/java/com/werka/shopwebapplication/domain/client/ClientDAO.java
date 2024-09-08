package com.werka.shopwebapplication.domain.client;

import com.werka.shopwebapplication.domain.common.BaseDao;
import java.sql.*;

public class ClientDAO extends BaseDao {

    public void save(Client client) {
        final String sql = "INSERT INTO clients (email, password, first_name, surname) VALUES (?, ?, ?, ?)";
        try(Connection connection = getConnection();
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
        final String sql = "SELECT first_name FROM clients WHERE email= ? AND password= ?";
        try(Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public boolean isMailFree(String email){
        final String sql = "SELECT first_name FROM clients WHERE email= ?";
        try(Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

}
