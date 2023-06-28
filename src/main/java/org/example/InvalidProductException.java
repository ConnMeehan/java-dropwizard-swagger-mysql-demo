package org.example;

public class InvalidProductException extends Exception {
    public InvalidProductException(String error) {
        super(error);
    }
}
