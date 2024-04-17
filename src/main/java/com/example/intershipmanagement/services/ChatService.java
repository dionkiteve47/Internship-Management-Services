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
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

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
    public void deleteChat(long chatId) {
        Optional<Chat> chatOptional = chatRepository.findById(chatId);
        if (chatOptional.isPresent()) {
            Chat chat = chatOptional.get();
            // Remove the association with users
            for (User user : chat.getUsers()) {
                user.getChats().remove(chat);
                userRepository.save(user); // Update user entity
            }
            // Delete the chat entity
            chatRepository.deleteById(chatId);
        } else {
            // Handle chat not found
            log.info("Chat not found with id: {}", chatId);
        }
    }

    @Override
    public Chat updateChat(Chat chat) {
        return chatRepository.save(chat);
    }


    // ADD MESSAGE
    public Chat addMessage(Long chatId, Message message) {
        Date currentDate = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
        message.setDate(currentDate);
        Chat chat = chatRepository.findById(chatId)
                .orElseThrow(() -> new IllegalArgumentException("Chat not found with id: " + chatId));
        chat.getMessages().add(message);
        message.setChat(chat);
        return chatRepository.save(chat);

    }
   // CREATE CHAT + ADD USER
    public Chat createChatWithUser(User user, String title) {
        Chat newChat = new Chat();
        newChat.setTitre(title);
        if (newChat.getUsers() == null) { newChat.setUsers(new HashSet<>()); }
        newChat.getUsers().add(user);
        if (user.getChats().isEmpty()) { user.setChats(new HashSet<>()); }
          user.getChats().add(newChat);
        return chatRepository.save(newChat);
    }


}
