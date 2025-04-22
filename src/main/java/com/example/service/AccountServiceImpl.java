package com.example.service;

import com.example.entity.Account;
import com.example.repository.AccountRepository;
import com.example.exception.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService{
    @Autowired
    private AccountRepository accountRepo;

    @Override
    public Account register(Account account) throws UsernameAlreadyExistsException, InvalidAccountException {
        if(account.getUsername()== null|| account.getUsername().trim().isEmpty()){
            throw new InvalidAccountException();
        }
        if(account.getPassword()==null || account.getPassword().length()<4){
            throw new InvalidAccountException();
        }
        if(accountRepo.findByUsername(account.getUsername()).isPresent()){
            throw new UsernameAlreadyExistsException;
        }
        return accountRepo.save(account);
    }

    @Override
    public Account login(String username, String password){
        return accountRepo.findByUsernameAndPassword(username, password).orElse(null);
    }







}
