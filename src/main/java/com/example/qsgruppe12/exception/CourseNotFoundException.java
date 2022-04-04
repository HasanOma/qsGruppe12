package com.example.qsgruppe12.exception;

public class CourseNotFoundException extends Exception{

    private static final String DEFAULT = "Course was not found";

    public CourseNotFoundException(String message){
        super(message);
    }

    public CourseNotFoundException(){
        super(DEFAULT);
    }
}
