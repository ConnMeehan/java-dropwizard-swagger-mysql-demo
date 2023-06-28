package org.example;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class OrderRequest {
    private int customerId;
    private Date orderDate;

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public java.sql.Date getOrderDate() {
        return (java.sql.Date) orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    @JsonCreator
    public OrderRequest(
            @JsonProperty("customerId") int customerId,
            @JsonProperty("orderDate") Date orderDate) {
        this.customerId = customerId;
        this.orderDate = orderDate;
    }
}