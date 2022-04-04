package com.example.qsgruppe12.exception;

public class UserNotFoundException extends Exception {

    private static final String DEFAULT = "File type is not supported";

    public UserNotFoundException(String message){
        super(message);
    }

    public UserNotFoundException(){
        super(DEFAULT);
    }
}
