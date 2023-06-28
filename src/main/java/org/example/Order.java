package org.example;

import java.util.Date;

public class Order implements Comparable<Order> {
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    private int orderId;
    private int customerId;
    private Date orderDate;

    public Order(int orderId, int customerId, Date orderDate) {
        setOrderId(orderId);
        setCustomerId(customerId);
        setOrderDate(orderDate);
    }

    @Override
    public int compareTo(Order order) {
        return this.getOrderDate().compareTo(order.getOrderDate());
    }

    @Override
    public String toString() {
        return "Order ID: " + this.getOrderId() + ", Customer ID: " + this.getCustomerId() + ", Order Date: " + this.getOrderDate();
    }
}