package com.example.service;

import com.example.entity.Account;
import com.example.repository.AccountRepository;
import com.example.exception.UsernameAlreadyExistsException;
import com.example.exception.InvalidAccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AccountServiceImpl implements AccountService{
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Account register(Account account) throws UsernameAlreadyExistsException, InvalidAccountException {
        if(account.getUsername()== null|| account.getUsername().isBlank()){
            throw new InvalidAccountException("Username cannot be blank.");
        }
        if(account.getPassword()==null || account.getPassword().length()<4){
            throw new InvalidAccountException("Password must be at least 4 characters long.");
        }
        if(accountRepo.findByUsername(account.getUsername()).isPresent()){
            throw new UsernameAlreadyExistsException("Username already exists.");
        }
        return accountRepository.save(account);
    }

    @Override
    public Account login(String username, String password){
        Account existing = accountRepository.findByUsername(username);
        return (existing != null && existing.getPassword().equals(password))? existing : null;
    }







}
