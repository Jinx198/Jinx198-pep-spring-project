package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.*;
import java.util.List;
import java.util.Map;
import com.example.entity.*;
import com.example.service.*;
import com.example.exception.*;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
@RestController
@RequestMapping
public class SocialMediaController {

@Autowired
private Acc accountService;

@Autowired
private MessageService messageService;

@PostMapping("/register")
public ResponseEntity<?> register(@RequestBody Account account) {
    try{
        Account newAccount = accountService.register(account);
        return ResponseEntity.ok(newAccount);
    } catch(UsernameAlreadyExistsException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Username already exists");
    } catch(InvalidAccountException e){
        return ResponseEntity.badRequest().body("Invalid account details");
    }
}

// Exception handler for InvalidMessageException
@ExceptionHandler(InvalidMessageException.class)
public ResponseEntity<String> handleInvalidMessageException(InvalidMessageException e) {
    return ResponseEntity.badRequest().body(e.getMessage());
}

//login request
@PostMapping("/login")
public ResponseEntity<?> login(@RequestBody Account account){
    Account loggedIn = accountService.login(account.getUsername(), account.getPassword());
    if(loggedIn != null){
        return ResponseEntity.ok(loggedIn);
    }else{
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
    }
}

//message request
@PostMapping("/messages")
public ResponseEntity<?> postMessage(@RequestBody Message message){
    try{
        Message saved = messageService.createMessage(message);
        return ResponseEntity.ok(saved);
    }catch(InvalidMessageException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}

//get all messages
@GetMapping("/messages")
public List<Message> getAllMessages(){
    return messageService.getAllMessages();
}

//get message by id
@GetMapping("/messages/{id}")
public ResponseEntity<?> getMessageById(@PathVariable int id){
    Message msg = messageService.getMessageById(id);
    return msg != null ? ResponseEntity.ok(msg) : ResponseEntity.ok().build();
    //return msg != null ? ResponseEntity.ok(msg) : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Message not found");
}


//delete message by id
@DeleteMapping("/messages/{id}")
public ResponseEntity<?> deleteMessage(@PathVariable int id) {
    int rowsDeleted = messageService.deleteMessage(id);
    return rowsDeleted == 1 ? ResponseEntity.ok(1) : ResponseEntity.ok().build();
    //return rowsDeleted == 1 ? ResponseEntity.ok(1) : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Message not found");
}

//update message text
@PatchMapping("/messages/{id}")
public ResponseEntity<?> updateMessage(@PathVariable int id, @RequestBody Map<String, String> body){
    String newText = body.get("messageText");
    try{
        int updated = messageService.updateMessage(id, newText);
        return ResponseEntity.ok(updated);
    }catch(InvalidMessageException e){
        return ResponseEntity.badRequest().body("Invalid message update");
    }
}

//get all messages from a user
@GetMapping("/accounts/{id}/messages")
public List<Message> getMessageByUser(@PathVariable int id){
    return messageService.getMessagesByAccountId(id);
}

}
