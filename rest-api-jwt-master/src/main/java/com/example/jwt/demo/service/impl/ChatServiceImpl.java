package com.example.jwt.demo.service.impl;

import com.example.jwt.demo.service.ChatService;
import org.alicebot.ab.Chat;
import org.alicebot.ab.History;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatServiceImpl implements ChatService {

    @Autowired
    private Chat chatSession;

    @Autowired
    private BmiServiceImpl bmiService;

    @Override
    public String getChatResponse(String message) {
        String response = chatSession.multisentenceRespond(message);
        // response = " BMI: height:168 age:22 ... "
        History<String> history = chatSession.inputHistory;
        return chatSession.multisentenceRespond(message);
    }
}
