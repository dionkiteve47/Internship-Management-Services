package com.example.intershipmanagement.services;

import com.example.intershipmanagement.entities.Chat;

import java.util.List;

public interface IChatService {

    Chat addChat(Chat chat);
    List<Chat> getAllChats();
    Chat getChatById(long idChat);
    void deleteChat(long idChat);
    Chat updateChat(Chat Chat);


}

