package com.assignment.productdemo.exception;

public class ProductException extends RuntimeException {
    private String message;

    public String getMessage() {
        return message;
    }

    public ProductException(String message) {
        super(message);
        this.message = message;
    }

    public ProductException() {
        super();
    }
}
