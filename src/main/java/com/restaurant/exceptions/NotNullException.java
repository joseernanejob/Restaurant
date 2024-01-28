package com.restaurant.exceptions;

public class NotNullException extends RuntimeException{

    public NotNullException(String message) {
        super(message);
    }
}
