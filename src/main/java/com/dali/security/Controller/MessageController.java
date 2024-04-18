package com.dali.security.Controller;


import com.dali.security.Entity.Message;
import com.dali.security.Service.IMessageService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
