package com.example.springmvc.exception;

public class UsernameIsNotCorrectException extends Exception {

    public UsernameIsNotCorrectException(String message) {
        super(message);
    }
}
