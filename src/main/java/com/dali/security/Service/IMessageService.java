package com.dali.security.Service;


import com.dali.security.Entity.Message;

import java.util.List;

public interface IMessageService {

    Message addMessage(Message message);
    List<Message> getAllMessages();
    Message getMessageById(long idMessage);
    void deleteMessage(long idMessage);
    Message updateMessage(Message idMessage);
}
