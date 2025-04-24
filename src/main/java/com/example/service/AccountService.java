package com.example.service;

import com.example.entity.Account;
import com.example.exception.InvalidAccountException;
import com.example.exception.UsernameAlreadyExistsException;

//due to a bug, AccountService will be changed to acc for the test to read it.

public interface AccountService{
    Account register(Account account) throws UsernameAlreadyExistsException, InvalidAccountException;
    Account login(String username, String password);
}
