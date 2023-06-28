package org.example;

import java.sql.*;
import java.util.List;

public class OrderService {
    private OrderDao orderDao = new OrderDao();
    private CustomerDao customerDao = new CustomerDao();
    private OrderValidator orderValidator = new OrderValidator(customerDao);

    public int createOrder(OrderRequest order) throws FailedToCreateOrderException, InvalidOrderException {

        try {
            String validation = orderValidator.isValidOrder(order);

            if (validation != null) {
                throw new InvalidOrderException(validation);
            }

            int id = orderDao.createOrder(order);

            if (id == -1) {
                throw new FailedToCreateOrderException();
            }

            return id;
        } catch (SQLException e) {
            System.err.println(e.getMessage());

            throw new FailedToCreateOrderException();
        }
    }
    public void updateOrder(int id, OrderRequest order) throws InvalidOrderException, OrderDoesNotExistException, FailedToUpdateOrderException {
        try {
            String validation = orderValidator.isValidOrder(order);

            if (validation != null) {
                throw new InvalidOrderException(validation);
            }

            Order orderToUpdate = orderDao.getOrderById(id);

            if (orderToUpdate == null) {
                throw new OrderDoesNotExistException();
            }

            orderDao.updateOrder(id, order);
        } catch (SQLException e) {
            System.err.println(e.getMessage());

            throw new FailedToUpdateOrderException();
        }
    }
    public List<Order> getAllOrders() throws FailedToGetOrdersException {
        try {
            List<Order> orderList = orderDao.getAllOrders();

            return orderList;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new FailedToGetOrdersException();
        }

        // Collections.sort(orderList);

        // 23
        // List<Order> lastWeek = orderList.stream().filter(order -> order.getOrderDate().compareTo(new Date(2023-06-27)) > 7).collect(Collectors.toList());
        // lastWeek.forEach(System.out::println);

        // 24
        // List<Order> customerID1 = orderList.stream().filter(order -> order.getCustomerId() == 1).collect(Collectors.toList());
        // customerID1.forEach(System.out::println);

        // 26
        // System.out.println("Newest order date: " + Collections.min(orderList));

        // 27
        // System.out.println("Total count: " + orderList.size());

        // 28
        // Integer mostOrders = orderList.stream()
        //        .collect(Collectors.groupingBy(Order::getCustomerId, Collectors.counting()))
        //        .entrySet().stream().max(Map.Entry.comparingByValue())
        //        .map(Map.Entry::getKey).orElse(null);
        // System.out.println("Customer with most orders: " + mostOrders);

        // 29
        // Integer leastOrders = orderList.stream()
        //        .collect(Collectors.groupingBy(Order::getCustomerId, Collectors.counting()))
        //        .entrySet().stream().min(Map.Entry.comparingByValue())
        //        .map(Map.Entry::getKey).orElse(null);
        // System.out.println("Customer with least orders: " + leastOrders);

        // 28 & 29 Other solution
//        Map<Integer, Long> countOrderMap = orderList.stream()
//                .collect(Collectors.groupingBy(Order::getCustomerId, Collectors.counting()));
//
//        System.out.println();
    }
    public Order getOrderById(int id) throws FailedToGetOrdersException, OrderDoesNotExistException {
        try {
            Order order = orderDao.getOrderById(id);

            if (order == null) {
                throw new OrderDoesNotExistException();
            }
            return order;
        } catch (SQLException e) {
            System.err.println(e.getMessage());

            throw new FailedToGetOrdersException();
        }
    }
}