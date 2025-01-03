package com.werka.shopwebapplication.domain.client;

import com.werka.shopwebapplication.config.DataHelper;
import com.werka.shopwebapplication.domain.address.ClientAddressData;
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
                int id = generatedKeys.getInt(1);
                client.setId(id);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isFoundClientByMailAndPass(String email, String password){
        final String sql = "SELECT `id` FROM clients WHERE email= ? AND password= ?";
        try(Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                DataHelper.setClientId(resultSet.getInt("id"));
                System.out.println("client id: " + DataHelper.getClientId());
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

    public boolean isPasswordCorrect(String password) {
        final String sql = "SELECT `id` FROM clients WHERE password= ?";
        try(Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public Client getClientDataByClientId(int id) {
        final String sql = "SELECT * FROM clients WHERE id = ?";
        Client result = null;
        try (Connection connection = getConnection();
             PreparedStatement  statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int idd = resultSet.getInt("id");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                String name = resultSet.getString("first_name");
                String surname = resultSet.getString("surname");
                if(email != null) {
                    result = new Client(idd, name, surname, email, password);
                }
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateClientNameAndSurname(String name, String surname) {
        final String sql = "UPDATE clients SET first_name = ?, surname = ? WHERE id = ?";
        try (Connection connection = getConnection();
             PreparedStatement  statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            statement.setString(2, surname);
            statement.setInt(3, DataHelper.getClientId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updatePassword(String newPassword) {
        final String sql = "UPDATE clients SET password = ? WHERE id = ?";
        try (Connection connection = getConnection();
             PreparedStatement  statement = connection.prepareStatement(sql)) {
            statement.setString(1, newPassword);
            statement.setInt(2, DataHelper.getClientId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
