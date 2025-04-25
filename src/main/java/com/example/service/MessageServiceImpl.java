package com.example.service;


import java.util.List;
import com.example.entity.Message;
import com.example.entity.Account;
import com.example.repository.MessageRepository;
import com.example.repository.AccountRepository;
import com.example.exception.InvalidMessageException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


//service implementation for handling message operations.
@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private AccountRepository accountRepository;

    //creates a new message after validating the text and user.
    @Override
    public Message createMessage(Message message) throws InvalidMessageException {
        if (message.getMessageText() == null || message.getMessageText().isBlank() || message.getMessageText().length() > 254) {
            throw new InvalidMessageException("Invalid message text.");
        }
        Account account = accountRepository.findById(message.getPostedBy()).orElse(null);
        if (account==null) {
            throw new InvalidMessageException("User does not exist.");
        }

        return messageRepository.save(message);
    }

    //retrieves all messages from the repository. 
    @Override
    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    //find a message by id. 
    @Override
    public Message getMessageById(int id) {
        return messageRepository.findById(id).orElse(null);
    }

    //deletes a message by id. 
    @Override
    public int deleteMessage(int id) {
        if (messageRepository.existsById(id)) {
            messageRepository.deleteById(id);
            return 1;
        }
        return 0;
    }

    //updates the text of an existing message. 
    @Override
    public int updateMessage(int id, String newText) throws InvalidMessageException {
        if (newText == null || newText.isBlank() || newText.length() > 255) {
            throw new InvalidMessageException("Invalid message text.");
        }

        Message existing = messageRepository.findById(id).orElse(null);
        if (existing==null) {
            throw new InvalidMessageException("Message does not exist.");
        }

        existing.setMessageText(newText);
        messageRepository.save(existing);
        return 1;
    }

    //retrives all messages posted by a specific account. 
    @Override
    public List<Message> getMessagesByAccountId(int accountId) {
        return messageRepository.findByPostedBy(accountId);
    }
}

