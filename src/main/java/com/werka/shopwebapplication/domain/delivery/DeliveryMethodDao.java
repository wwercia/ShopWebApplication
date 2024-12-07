package com.werka.shopwebapplication.domain.delivery;

import com.werka.shopwebapplication.domain.CurrentOrderId;
import com.werka.shopwebapplication.domain.common.BaseDao;

import java.sql.*;

public class DeliveryMethodDao extends BaseDao {

    public DeliveryMethod findDeliveryMethodByOrderId(int orderId) {
        final String sql = "SELECT * FROM delivery WHERE `order_id` = ?";
        DeliveryMethod result = null;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, orderId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                result = getResultDeliveryMethod(resultSet);
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private DeliveryMethod getResultDeliveryMethod(ResultSet resultSet) throws SQLException {
        String deliveryName = resultSet.getString("method");
        return DeliveryMethod.valueOf(deliveryName);
    }

    public void saveDeliveryMethod(String method, int orderId) {
        final String sql = "INSERT INTO delivery (method, `order_id`) VALUES (?, ?)";
        try(Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1, method);
            statement.setInt(2, orderId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isDeliverySaved() {
        DeliveryMethod deliveryMethod = findDeliveryMethodByOrderId(CurrentOrderId.getOrderId());
        if(deliveryMethod == null || deliveryMethod.getDisplayName().isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    public void updateDeliveryMethod(String method) {
        final String sql = "UPDATE delivery SET method = ? WHERE `order_id` = ?";
        try(Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1, method);
            statement.setInt(2, CurrentOrderId.getOrderId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
