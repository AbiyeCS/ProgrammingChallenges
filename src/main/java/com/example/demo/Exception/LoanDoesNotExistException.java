package com.example.demo.Exception;

public class LoanDoesNotExistException extends RuntimeException {
    public LoanDoesNotExistException(String message) {
        super(message);
    }
}
