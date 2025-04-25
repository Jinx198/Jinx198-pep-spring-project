package com.example.exception;

//exception for when the message values don't match.
public class InvalidMessageException extends Exception{
    public InvalidMessageException(){
        super();
    }
    public InvalidMessageException(String message){
        super(message);
    }
}
