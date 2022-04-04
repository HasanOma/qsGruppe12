package com.example.qsgruppe12.exception;

public class QueueException extends Exception{

    private static final String DEFAULT = "Queue could not be reached";

    public QueueException(String message){
        super(message);
    }

    public QueueException(){
        super(DEFAULT);
    }
}
