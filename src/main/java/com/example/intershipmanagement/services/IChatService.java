package com.example.intershipmanagement.services;

import com.example.intershipmanagement.entities.Chat;
import com.example.intershipmanagement.entities.Message;
import com.example.intershipmanagement.entities.User;

import java.util.List;

public interface IChatService {

    Chat addChat(Chat chat);
    List<Chat> getAllChats();
    Chat getChatById(long idChat);
    void deleteChat(long idChat);
    Chat updateChat(Chat Chat);
    public Chat addMessage(Long chatId, Message message);
    public Chat createChatWithUser(User user, String title);

}

