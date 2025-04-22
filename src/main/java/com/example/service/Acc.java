package com.example.service;
import com.example.entity.InvalidAccountException;
import com.example.exception.UsernameAlreadyExistsException;

public interface AccountServ{
    Account register(Account account) throws UsernameAlreadyExistsException, InvalidAccountException;
    Account login(String username, String password);
}
