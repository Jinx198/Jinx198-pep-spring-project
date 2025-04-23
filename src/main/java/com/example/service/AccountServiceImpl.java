package com.example.service;

import com.example.entity.Account;
import com.example.repository.AccountRepository;
import com.example.exception.UsernameAlreadyExistsException;
import com.example.exception.InvalidAccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AccountServiceImpl implements Acc{
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
        if(accountRepository.findByUsername(account.getUsername()).isPresent()){
            throw new UsernameAlreadyExistsException("Username already exists.");
        }
        return accountRepository.save(account);
    }

    @Override
    public Account login(String username, String password){
        return accountRepository.findByUsername(username).filter(acc-> acc.getPassword().equals(password)).orElse(null);
        
    }

}
