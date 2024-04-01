package com.example.intershipmanagement.controllers;


import com.example.intershipmanagement.entities.Message;

import com.example.intershipmanagement.services.IMessageService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@AllArgsConstructor
@Slf4j
@RestController
@RequestMapping("api/message")
public class MessageController {
    IMessageService messageService;


    @PostMapping("add")
    public Message addingMessage(@RequestBody Message message){
        return messageService.addMessage(message);
    }



    @GetMapping("getAll")
    public List<Message> gettingAllMessage(){
        return messageService.getAllMessages();
    }


    @GetMapping("get")
    public Message gettingMessage(@RequestParam("idMessage") long idMessage){
        return messageService.getMessageById(idMessage);
    }


    @DeleteMapping("delete/{idMessage}")
    public void deletingMessage(@PathVariable("idMessage") long idMessage){
        messageService.deleteMessage(idMessage);
    }


    @PutMapping("update")
    public Message updatingMessage(@RequestBody Message message){
        return messageService.updateMessage(message);
    }


}
