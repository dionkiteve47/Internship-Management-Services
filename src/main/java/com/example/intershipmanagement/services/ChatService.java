package com.example.intershipmanagement.services;

import com.example.intershipmanagement.entities.Chat;
import com.example.intershipmanagement.repositories.ChatRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChatService implements IChatService {
    ChatRepository chatRepository;
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
}
