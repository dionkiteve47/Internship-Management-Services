package com.example.intershipmanagement.controllers;

import com.example.intershipmanagement.entities.Chat;
import com.example.intershipmanagement.services.IChatService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@AllArgsConstructor
@Slf4j
@RestController
@RequestMapping("api/chat")

public class ChatController {
    IChatService chatService;

    @PostMapping("add")
    public Chat addingChat(@RequestBody Chat chat){
        return chatService.addChat(chat);
    }

    // Order 2
    @GetMapping("getAll")
    public List<Chat> gettingAllChat(){
        return chatService.getAllChats();
    }

    // Order 3
    @GetMapping("get")
    public Chat gettingChat(@RequestParam("idChat") long idChat){
        return chatService.getChatById(idChat);
    }

    // Order 4
    @DeleteMapping("delete/{idChat}")
    public void deletingChat(@PathVariable("idChat") long idChat){
        chatService.deleteChat(idChat);
    }

    // Order 5
    @PutMapping("update")
    public Chat updatingChat(@RequestBody Chat chat){
        return chatService.updateChat(chat);
    }
}
