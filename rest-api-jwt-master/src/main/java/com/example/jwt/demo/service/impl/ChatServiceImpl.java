package com.example.jwt.demo.service.impl;

import com.example.jwt.demo.service.ChatService;
import org.alicebot.ab.Chat;
import org.alicebot.ab.History;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        String topic = chatSession.predicates.get("topic");
        int height = Integer.parseInt(chatSession.predicates.get("user_height"));
        int weight = Integer.parseInt(chatSession.predicates.get("user_weight"));
        int old  = Integer.parseInt(chatSession.predicates.get("user_age"));
        int gender = 0;
        if(chatSession.predicates.get("user_gender").toLowerCase()==null){
            response ="Cho tôi biết giưới tính của bạn";
        }else{
            if (chatSession.predicates.get("user_gender").toLowerCase().equals("nam") ){
                gender = 1;
            }else {
                gender = 0;
            }
        }

        switch (topic) {
            case "BMI-cal":
                if(height == 0){
                  response = "Vui lòng cho tôi biết chiều cao của bạn";
                }else if (weight==0) {
                    response = "Vui lòng cho tôi biết cân nặng của bạn";
                }
                else{
                    response = BMi(height,weight);
                }
                break;
            case "BRM-cal":
                if(height == 0){
                    response = "Vui lòng cho tôi biết chiều cao của bạn";
                }else if (weight==0) {
                    response = "Vui lòng cho tôi biết cân nặng của bạn";
                } else if(old==0){
                    response = "Vui lòng cho tôi biết tuổi của Bạn";
                }
                else {
                    response = "Bạn nên ăn " + BRM(weight,height,old,gender)+ "một ngày" ;
                }
                break;
            case "Hien-tuong":
                break;
            case "Che-do":
                break;
            case "kich-ban":
                break;
        }
        return response;
    }

    private String BMi(int canNang , int chieuCao){
        int  bmi = canNang/(chieuCao/100);
        if (bmi<19){
            return "Bạn hơi gầy";
        }else if(bmi>25){
            return "Bạn hơi nặng cân";
        }else{
            return "Bạn có một thân hình đẹp";
        }
    }

    private float BRM(int cangNang , int chieuCao, int old,int gender){
        float calo;
        if (gender == 1) {
            calo = ((float) 9.99 * cangNang) + ((float) 6.25 * ((float) chieuCao / 100)) - ((float) 4.92 * old) + 5;

        } else {
            calo = ((float) 9.99 * cangNang) + ((float) 6.25 * ((float) chieuCao / 100)) - ((float) 4.92 * old) - 161;
        }
        return calo;
    }

}
