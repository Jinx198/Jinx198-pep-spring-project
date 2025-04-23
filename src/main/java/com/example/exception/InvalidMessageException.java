package com.example.exception;

public class InvalidMessageException extends Exception{
    public InvalidMessageException(){
        super();
    }
    public InvalidMessageException(String message){
        super(message);
    }
}
