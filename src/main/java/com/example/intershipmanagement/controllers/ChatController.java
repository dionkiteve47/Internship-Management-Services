package com.example.intershipmanagement.controllers;

import com.example.intershipmanagement.entities.Chat;
import com.example.intershipmanagement.entities.Message;
import com.example.intershipmanagement.entities.User;
import com.example.intershipmanagement.services.IChatService;
import com.example.intershipmanagement.services.IUserService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;


@AllArgsConstructor
@Slf4j
@RestController
@RequestMapping("api/chat")
@CrossOrigin(origins = "http://localhost:4200")
public class ChatController {


    IChatService chatService;
    IUserService userService;

    @PostMapping("add")
    public Chat addingChat(@RequestBody Chat chat){
        return chatService.addChat(chat);
    }

    @GetMapping("getAll")
    public List<Chat> gettingAllChat(){
        log.info("test");
        return chatService.getAllChats();
    }

    @GetMapping("get")
    public Chat gettingChat(@RequestParam("idChat") long idChat){return chatService.getChatById(idChat);}


    @DeleteMapping("delete/{idChat}")
    public void deletingChat(@PathVariable("idChat") long idChat){
        chatService.deleteChat(idChat);
    }


    @PutMapping("update")
    public Chat updatingChat(@RequestBody Chat chat){
        return chatService.updateChat(chat);
    }

    // ADD MESSAGE TO CHAT  BY ID CHAT
    @PostMapping("/{chatId}/messages")
    public ResponseEntity<Set<Message>> addMessageToChat(@PathVariable("chatId") long chatId, @RequestBody Message message) {
        Chat chat = chatService.addMessage(chatId, message);
        return new ResponseEntity<>(chat.getMessages(), HttpStatus.CREATED);
    }

    // GET CHAT MESSAGES BY ID CHAT
    @GetMapping("/{chatId}/messages")
    public ResponseEntity<Set<Message>> getChatMessages(@PathVariable("chatId") long chatId) {
        Chat chat = chatService.getChatById(chatId);
        return new ResponseEntity<>(chat.getMessages(), HttpStatus.OK);
    }


    // CREAT CHAT + ADD USER
    @PostMapping("/add/user")
    public ResponseEntity<?> createChatWithUser(@RequestBody long userId) {
        User user = userService.getUserById(userId);
        Chat newChat = chatService.createChatWithUser(user);
        return ResponseEntity.ok(newChat);
    }

}
