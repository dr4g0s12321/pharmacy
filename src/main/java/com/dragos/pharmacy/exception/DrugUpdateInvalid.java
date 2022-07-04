package com.dragos.pharmacy.exception;

public class DrugUpdateInvalid extends Exception{
    public DrugUpdateInvalid(String errorMessage) {
        super(errorMessage);
    }
}
