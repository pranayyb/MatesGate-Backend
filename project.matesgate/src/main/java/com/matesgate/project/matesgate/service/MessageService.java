package com.matesgate.project.matesgate.service;

import com.matesgate.project.matesgate.model.Message;

import java.util.List;

public interface MessageService {
    Message saveMessage(Message message);
    Message getMessageById(Integer id);
    List<Message> getAllMessages();
    void deleteMessage(Integer id);
}
