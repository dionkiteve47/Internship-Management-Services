package com.example.intershipmanagement.services;

import com.example.intershipmanagement.entities.Chat;
import com.example.intershipmanagement.entities.Message;
import com.example.intershipmanagement.entities.User;
import com.example.intershipmanagement.repositories.ChatRepository;
import com.example.intershipmanagement.repositories.UserRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChatService implements IChatService {
    ChatRepository chatRepository;
    UserRepository userRepository;
    
    @Override
    public Chat addChat(Chat chat) {
        return chatRepository.save(chat);
    }

    @Override
    public List<Chat> getAllChats() {
        return (List<Chat>) chatRepository.findAll();
    }

    @Override
    public Chat getChatById(long idChat) {
        return chatRepository.findById(idChat).get();
    }

    @Override
    public void deleteChat(long idChat) {
           chatRepository.deleteById(idChat);
    }

    @Override
    public Chat updateChat(Chat chat) {
        return chatRepository.save(chat);
    }


    public Chat addMessage(Long chatId, Message message) {
        Chat chat = chatRepository.findById(chatId)
                .orElseThrow(() -> new IllegalArgumentException("Chat not found with id: " + chatId));
        chat.getMessages().add(message);
        message.setChat(chat);
        return chatRepository.save(chat);
    }

    public Chat createChatWithUser(User user) {
        Chat newChat = new Chat();
        newChat.setTitre("Chat with " + user.getNomUser());
        if (newChat.getUsers() == null) { newChat.setUsers(new HashSet<>()); }
        newChat.getUsers().add(user);
        if (user.getChats().isEmpty()) { user.setChats(new HashSet<>()); }
          user.getChats().add(newChat);
        return chatRepository.save(newChat);
    }


}
