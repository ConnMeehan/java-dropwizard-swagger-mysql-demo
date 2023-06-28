package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {
    private DatabaseConnector databaseConnector = new DatabaseConnector();

    public List<Product> getAllProducts() throws SQLException {
            Connection c = databaseConnector.getConnection();

            Statement st = c.createStatement();

            ResultSet rs = st.executeQuery("SELECT ProductID, Name, Description, Price FROM `Product`;");

            List<Product> productList = new ArrayList<>();

            while (rs.next()) {
                Product product = new Product (
                        rs.getInt("ProductID"),
                        rs.getString("Name"),
                        rs.getString("Description"),
                        rs.getDouble("Price")
                );
                productList.add(product);
            }
            return productList;
    }
    public Product getProductById(int id) throws SQLException {
        Connection c = databaseConnector.getConnection();

        Statement st = c.createStatement();

        ResultSet rs = st.executeQuery("SELECT ProductID, Name, Description, Price" +
                " FROM Product where ProductID=" + id);

        while (rs.next()) {
            return new Product(
                    rs.getInt("ProductID"),
                    rs.getString("Name"),
                    rs.getString("Description"),
                    rs.getDouble("Price")
            );
        }
        return null;
    }
    public int createProduct(ProductRequest product) throws SQLException {
        Connection c = databaseConnector.getConnection();

        String insertStatement = "INSERT INTO Product (NAME, Description, Price) VALUES (?, ?, ?)";

        PreparedStatement st = c.prepareStatement(insertStatement, Statement.RETURN_GENERATED_KEYS);

        st.setString(1, product.getName());
        st.setString(2, product.getDescription());
        st.setDouble(3, product.getPrice());

        st.executeUpdate();

        ResultSet rs = st.getGeneratedKeys();

        if (rs.next()) {
            return rs.getInt(1);
        }
        return -1;
    }
    public void updateProduct(int id, ProductRequest product) throws SQLException {
        Connection c = databaseConnector.getConnection();

        String updateStatement = "UPDATE Product SET Name = ?, Description = ?, Price = ? WHERE ProductID = ?";

        PreparedStatement st = c.prepareStatement(updateStatement);

        st.setString(1, product.getName());
        st.setString(2, product.getDescription());
        st.setDouble(3, product.getPrice());
        st.setInt(4, id);

        st.executeUpdate();
    }
}