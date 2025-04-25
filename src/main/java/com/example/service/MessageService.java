package com.example.service;

import com.example.entity.Message;
import com.example.exception.InvalidMessageException;

import java.util.List;

public interface MessageService {
    //creates a new message after validating it's content and user existence.
    Message createMessage(Message message) throws InvalidMessageException;
    //retrieves all messages from the system. 
    List<Message> getAllMessages();
    //retrives a message by it's id.
    Message getMessageById(int id);
    //deletes a message by it's id.
    int deleteMessage(int id);
    //updates the text of a message by it's id. 
    int updateMessage(int id, String newText) throws InvalidMessageException;
    //retrieves all messages posted by a specific account. 
    List<Message> getMessagesByAccountId(int accountId);
}
