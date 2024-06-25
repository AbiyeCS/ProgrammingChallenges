package com.example.demo.Exception;

public class MemberDoesNotExistException extends RuntimeException {
    public MemberDoesNotExistException(String message) {
        super(message);
    }
}
