package com.example.repository;

import com.example.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

//Repository interface for performing CRUD operations on Account entities.
public interface AccountRepository extends JpaRepository<Account, Integer>{
    //finds account by it's username.
    Optional<Account> findByUsername(String username);
    //finds account by both username and password. 
    Optional<Account> findByUsernameAndPassword(String username, String password);
}
