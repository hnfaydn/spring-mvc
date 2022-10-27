package com.example.springmvc.exception;

public class PasswordIsNotCorrectException extends Exception{

    public PasswordIsNotCorrectException(String message) {
        super(message);
    }
}
