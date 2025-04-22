package com.example.service;

import com.example.entity.Message;
import com.example.exception.InvalidMessageException;

import java.util.List;

public interface MessageService {
    Message createMessage(Message message) throws InvalidMessageException;
    List<Message> getAllMessages();
    Message getMessageById(int id);
    int deleteMessage(int id);
    int updateMessage(int id, String newText) throws InvalidMessageException;
    List<Message> getMessagesByAccountId(int accountId);
}
