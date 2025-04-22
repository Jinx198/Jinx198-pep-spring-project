package com.example.service;


import java.util.List;
import com.example.entity.Message;
import com.example.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;




@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepository messageRepo;

    @Autowired
    private AccountRepository accountRepo;

    @Override
    public Message createMessage(Message message) throws InvalidMessageException {
        if (message.getMessageText() == null || message.getMessageText().isBlank() ||
            message.getMessageText().length() > 255) {
            throw new InvalidMessageException();
        }

        if (!accountRepo.existsById(message.getPostedBy())) {
            throw new InvalidMessageException();
        }

        return messageRepo.save(message);
    }

    @Override
    public List<Message> getAllMessages() {
        return messageRepo.findAll();
    }

    @Override
    public Message getMessageById(int id) {
        return messageRepo.findById(id).orElse(null);
    }

    @Override
    public int deleteMessage(int id) {
        if (messageRepo.existsById(id)) {
            messageRepo.deleteById(id);
            return 1;
        }
        return 0;
    }

    @Override
    public int updateMessage(int id, String newText) throws InvalidMessageException {
        if (newText == null || newText.isBlank() || newText.length() > 255) {
            throw new InvalidMessageException();
        }

        Optional<Message> optionalMessage = messageRepo.findById(id);
        if (optionalMessage.isPresent()) {
            Message message = optionalMessage.get();
            message.setMessageText(newText);
            messageRepo.save(message);
            return 1;
        }

        throw new InvalidMessageException();
    }

    @Override
    public List<Message> getMessagesByAccountId(int accountId) {
        return messageRepo.findByPostedBy(accountId);
    }
}

