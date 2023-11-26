package com.example.jwt.demo.service.impl;

import com.example.jwt.demo.service.ChatService;
import org.alicebot.ab.Chat;
import org.alicebot.ab.History;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.alicebot.ab.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

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
        String userHeight = extractValueFromSetTag(response, "user_height");
        System.out.println("User Height: " + userHeight);
        return response;
    }

    private String extractValueFromSetTag(String response , String setName) {
        String regex = "<set\\s+name\\s*=\\s*\"" + setName + "\"\\s*>\\s*<star index=\"2\"/></set>";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(response);

        if (matcher.find()) {
            return matcher.group(1);
        } else {
            return null;
        }
    }
}
