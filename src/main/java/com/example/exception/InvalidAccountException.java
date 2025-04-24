package com.example.exception;

//exception for when the account values don't match 
public class InvalidAccountException extends Exception{
    public InvalidAccountException(){
        super();
    }

    public InvalidAccountException(String message)
    {
        super(message);
    }

}
