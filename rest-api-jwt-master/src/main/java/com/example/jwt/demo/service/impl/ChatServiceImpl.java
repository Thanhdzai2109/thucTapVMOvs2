package com.example.jwt.demo.service.impl;

import com.example.jwt.demo.service.ChatService;
import org.alicebot.ab.Chat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatServiceImpl implements ChatService {

    @Autowired
    private Chat chatSession;

    @Override
    public String getChatResponse(String message) {
        return chatSession.multisentenceRespond(message);
    }
}
