package com.example.service;

import com.example.entity.Account;
import com.example.exception.InvalidAccountException;
import com.example.exception.UsernameAlreadyExistsException;

public interface AccountService{
    Account register(Account account) throws UsernameAlreadyExistsException, InvalidAccountException;
    Account login(String username, String password);
}
