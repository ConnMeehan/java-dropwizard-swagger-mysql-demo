package org.example;

public class InvalidOrderException extends Exception {
    public InvalidOrderException(String error) {
        super(error);
    }
}
