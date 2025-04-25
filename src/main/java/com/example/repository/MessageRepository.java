package com.example.repository;

import com.example.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

//Repository interface for performing CRUD operations on Message entities. 
public interface MessageRepository extends JpaRepository<Message, Integer> {
    //Retrieves all messages posted by a specific account. 
    List<Message> findByPostedBy(int accountId);
}