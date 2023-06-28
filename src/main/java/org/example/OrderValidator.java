package org.example;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;

public class OrderValidator {
    private CustomerDao customerDao;
    public OrderValidator(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }
    public String isValidOrder(OrderRequest order) {
        try {
            // Check if the customer exists in the database
            if (!customerDao.customerExists(order.getCustomerId())) {
                return "Customer ID does not exist in the database";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Get the current date
        LocalDate currentDate = LocalDate.now();
        // Check if the order date is older than one year
        LocalDate oneYearAgo = currentDate.minusYears(1);
        LocalDate orderDate = order.getOrderDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        if (orderDate.isBefore(oneYearAgo)) {
            return "Order date is older than one year";
        }
        return null;
    }
}
