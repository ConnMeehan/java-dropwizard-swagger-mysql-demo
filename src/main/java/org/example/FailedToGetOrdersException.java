package org.example;

public class FailedToGetOrdersException extends Throwable {
    @Override
    public String getMessage() {
        return "Failed to get orders from the database";
    }
}
