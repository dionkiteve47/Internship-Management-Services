package com.example.intershipmanagement.services;

import com.example.intershipmanagement.entities.Message;
import com.example.intershipmanagement.repositories.MessageRepository;
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
public class MessageService implements IMessageService{
    MessageRepository messageRepository;

    @Override
    public Message addMessage(Message message) {
        return messageRepository.save(message);
    }

    @Override
    public List<Message> getAllMessages() {
        return (List<Message>)messageRepository.findAll();
    }

    @Override
    public Message getMessageById(long idMessage) {
        return messageRepository.findById(idMessage).get();
    }

    @Override
    public void deleteMessage(long idMessage) {
        messageRepository.deleteById(idMessage);

    }


    @Override
    public Message updateMessage(Message idMessage) {
        return messageRepository.save(idMessage);
    }
}
