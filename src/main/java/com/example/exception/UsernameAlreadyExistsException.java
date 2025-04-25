package com.example.exception;

//exception for when the username already exists.
public class UsernameAlreadyExistsException extends Exception{
    public UsernameAlreadyExistsException()
    {
        super();
    }

    public UsernameAlreadyExistsException(String message){
        super(message);
    }
}
