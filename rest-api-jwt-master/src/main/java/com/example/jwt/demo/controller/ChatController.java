package com.example.jwt.demo.controller;

import com.example.jwt.demo.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/chat")
public class ChatController {
    @Autowired
    private ChatService chatService;

    @GetMapping("/request")
    public String getResponse(@RequestParam String userMessage) {
        return chatService.getChatResponse(userMessage);
    }
}
