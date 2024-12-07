package com.werka.shopwebapplication.domain.address;

import com.werka.shopwebapplication.domain.common.BaseDao;
import java.sql.*;

public class ClientAddressDataDao extends BaseDao {

    public void save(ClientAddressData clientData) {
        final String sql = "INSERT INTO addresses (client_id, phone_number, address, town, postcode) VALUES (?, ?, ?, ?, ?)";
        try(Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            statement.setInt(1, clientData.getClientId());
            statement.setString(2, clientData.getPhoneNumber());
            statement.setString(3, clientData.getAddress());
            statement.setString(4, clientData.getTown());
            statement.setString(5, clientData.getPostcode());
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if(generatedKeys.next()) {
                int id = generatedKeys.getInt(1);
                clientData.setId(id);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ClientAddressData getClientAddressDataByClientId(int clientId) {
        final String sql = "SELECT * FROM addresses WHERE client_id = ?";
        ClientAddressData result = null;
        try (Connection connection = getConnection();
             PreparedStatement  statement = connection.prepareStatement(sql)) {
            statement.setInt(1, clientId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int clientIdd = resultSet.getInt("client_id");
                String phoneNumber = resultSet.getString("phone_number");
                String address = resultSet.getString("address");
                String town = resultSet.getString("town");
                String postcode = resultSet.getString("postcode");
                if(phoneNumber != null) {
                    result = new ClientAddressData(id, clientIdd, phoneNumber, address, town, postcode);
                }
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
