package com.sergio.RegisterApp.exceptions;

public class DoctypeInvalidException extends Exception {
    public DoctypeInvalidException(String message) {
        super(message);
    }

    public DoctypeInvalidException() {
        super("Invalid doctype");
    }
}
