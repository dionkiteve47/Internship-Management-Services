package com.example.intershipmanagement.services;


import com.example.intershipmanagement.entities.Message;

import java.util.List;

public interface IMessageService {

    Message addMessage(Message message);
    List<Message> getAllMessages();
    Message getMessageById(long idMessage);
    void deleteMessage(long idMessage);
    Message updateMessage(Message idMessage);
}
