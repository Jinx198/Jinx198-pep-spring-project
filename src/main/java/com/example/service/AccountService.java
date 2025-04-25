package com.example.service;

import com.example.entity.Account;
import com.example.exception.InvalidAccountException;
import com.example.exception.UsernameAlreadyExistsException;

public interface AccountService{
    //registers a new account after validating input and checking for duplicates.
    Account register(Account account) throws UsernameAlreadyExistsException, InvalidAccountException;
    //authenticates a user based on username and password.
    Account login(String username, String password);
}
