package com.reel.reserve.exception;

public class EmailAlreadyRegisteredException extends Exception{
    public EmailAlreadyRegisteredException(String message) {
        super(message);
    }
}
