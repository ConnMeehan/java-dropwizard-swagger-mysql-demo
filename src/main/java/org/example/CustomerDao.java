package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDao {
    private DatabaseConnector databaseConnector = new DatabaseConnector();

    public boolean customerExists(int customerId) throws SQLException {
        Connection c = databaseConnector.getConnection();

        String query = "SELECT COUNT(*) FROM customers WHERE id = ?";

        try (PreparedStatement statement = c.prepareStatement(query)) {
            statement.setInt(1, customerId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count > 0;
                }
            }
        } catch (SQLException e) {
            // Handle any exceptions
            e.printStackTrace();
        }
        return false;
    }
}