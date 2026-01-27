package com.aditya.exception;

public class DuplicateOwnerIdException extends RuntimeException {
    public DuplicateOwnerIdException(String message) {
        super(message);
    }
}
