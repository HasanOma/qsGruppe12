package com.example.qsgruppe12.exception;

public class FileNotSupportedException extends Exception{

    private static final String DEFAULT = "File type is not supported";

    public FileNotSupportedException(String message){
        super(message);
    }

    public FileNotSupportedException(){
        super(DEFAULT);
    }
}