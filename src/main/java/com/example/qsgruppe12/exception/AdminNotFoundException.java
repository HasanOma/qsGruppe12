package com.example.qsgruppe12.exception;

public class AdminNotFoundException extends Exception{

    private static final String DEFAULT = "Restriction is not Granted";

    public AdminNotFoundException(String message){
        super(message);
    }

    public AdminNotFoundException(){
        super(DEFAULT);
    }

}
