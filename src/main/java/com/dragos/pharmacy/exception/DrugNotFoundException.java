package com.dragos.pharmacy.exception;

public class DrugNotFoundException extends Exception{
    public DrugNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
