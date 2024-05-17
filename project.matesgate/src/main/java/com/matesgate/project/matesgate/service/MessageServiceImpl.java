package com.matesgate.project.matesgate.service;

import com.matesgate.project.matesgate.model.Message;
import com.matesgate.project.matesgate.repository.MessageRepo;
import com.matesgate.project.matesgate.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepo messageRepository;

    @Override
    public Message saveMessage(Message message) {
        return messageRepository.save(message);
    }

    @Override
    public Message getMessageById(Integer id) {
        return messageRepository.findById(id).orElse(null);
    }

    @Override
    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    @Override
    public void deleteMessage(Integer id) {
        messageRepository.deleteById(id);
    }
}
