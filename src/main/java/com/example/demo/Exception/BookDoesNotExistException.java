package com.example.demo.Exception;

public class BookDoesNotExistException extends RuntimeException {
    public BookDoesNotExistException(String message) {
        super(message);
    }
}
