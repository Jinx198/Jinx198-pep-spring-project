package com.example.service;

public interface MessageService {
    Message createMessage(Message message) throws InvalidMessageException;
    List<Message> getAllMessages();
    Message getMessageById(int id);
    int deleteMessage(int id);
    int updateMessage(int id, String newText) throws InvalidMessageException;
    List<Message> getMessagesByAccountId(int accountId);
}
