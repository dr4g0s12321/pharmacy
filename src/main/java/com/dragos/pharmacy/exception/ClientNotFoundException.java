package com.dragos.pharmacy.exception;

public class ClientNotFoundException extends Exception{
    public ClientNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
