package com.dali.security.Service;

import com.dali.security.Entity.Chat;
import com.dali.security.Entity.Message;
import com.dali.security.Entity.User;

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

