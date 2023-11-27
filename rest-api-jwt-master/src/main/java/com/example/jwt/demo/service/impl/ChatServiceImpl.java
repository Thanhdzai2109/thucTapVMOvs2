package com.example.jwt.demo.service.impl;

import com.example.jwt.demo.constant.GioiTinhConst;
import com.example.jwt.demo.dto.ThucDon;
import com.example.jwt.demo.dto.Tre;
import com.example.jwt.demo.model.enums.GioiTinhEnum;
import com.example.jwt.demo.service.ChatService;
import com.example.jwt.demo.service.PhenomenonService;
import org.alicebot.ab.Chat;
import org.alicebot.ab.History;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChatServiceImpl implements ChatService {

    @Autowired
    private Chat chatSession;


    @Autowired
    private ThucDonService thucDonService;

    @Autowired
    private PhenomenonService hientuongService;

    @Override
    public String getChatResponse(String message) {
        String response = chatSession.multisentenceRespond(message);
        String topic = chatSession.predicates.get("topic");
        int height = Integer.parseInt(chatSession.predicates.get("user_height"));
        int weight = Integer.parseInt(chatSession.predicates.get("user_weight"));
        int old = Integer.parseInt(chatSession.predicates.get("user_age"));
        int month = Integer.parseInt(chatSession.predicates.get("month"));
        int gender = 0;
        if (chatSession.predicates.get("user_gender").toLowerCase() == null) {
            response = "Cho tôi biết giưới tính của bạn";
        } else {
            if (chatSession.predicates.get("user_gender").toLowerCase().equals("nam")) {
                gender = 1;
            } else {
                gender = 0;
            }
        }

        switch (topic) {
            case "BMI-cal":
                if (height == 0) {
                    response = "Vui lòng cho tôi biết chiều cao của bạn";
                } else if (weight == 0) {
                    response = "Vui lòng cho tôi biết cân nặng của bạn";
                } else {
                    response = BMi(height, weight);
                }
                break;
            case "BRM-cal":
                if (height == 0) {
                    response = "Vui lòng cho tôi biết chiều cao của bạn";
                } else if (weight == 0) {
                    response = "Vui lòng cho tôi biết cân nặng của bạn";
                } else if (old == 0) {
                    response = "Vui lòng cho tôi biết tuổi của Bạn";
                } else {
                    response = "Bạn nên ăn " + BRM(weight, height, old, gender) + "một ngày";
                }
                break;
            case "Hien-tuong":
                String[] hienTuongList = new String[0];
                if (hienTuongList == null || hienTuongList.length == 0) {
                    response = "Vui lòng cho biết hiện tượng";
                } else {
                    response = hientuongService.giaiPhap(hienTuongList);
                }
                break;
            case "kich-ban1":
                if (height == 0) {
                    response = "Vui lòng cho tôi biết chiều cao của trẻ";
                } else if (weight == 0) {
                    response = "Vui lòng cho tôi biết cân nặng của trẻ";
                } else if (month == 0) {
                    response = "Vui lòng cho tôi biết tuổi của trẻ";
                }
                else if(chatSession.predicates.get("user_gender").toLowerCase() == null){
                    response = "Vui lòng cho tôi biết giới tính của trẻ";
                }
                else {
                   response = thucDonTheoTheTrang( chatSession.predicates.get("user_gender"),  month,  height, weight).toString();
                }
                break;
            case "kich-ban2":
                response = thucDonTheoHienTuong(message);
                break;
        }
        return response;
    }

    private String BMi(int canNang, int chieuCao) {
        int bmi = canNang / (chieuCao / 100);
        if (bmi < 19) {
            return "Bạn hơi gầy";
        } else if (bmi > 25) {
            return "Bạn hơi nặng cân";
        } else {
            return "Bạn có một thân hình đẹp";
        }
    }

    private ThucDon thucDonTheoTheTrang(String sex, int month, int height, int weight) {
        if(sex.equals( GioiTinhEnum.Nam)){
            sex = GioiTinhConst.nam;
        }else {
            sex = GioiTinhConst.nu;
        }
        if (month < 24 || month > 60
                || height <= 0 || weight <= 0) {
            ThucDon thucDon = new ThucDon();
            thucDon.setMessage("Dữ liệu không hợp lệ");
            return thucDon;
        }
        return thucDonService.thucDonDinhDuong(sex, month, weight, height);
    }

    // kịch bản 2: Chế độ ăn theo hiện tượng
    private String thucDonTheoHienTuong(String message) {

        if (message.equals("")) return "Hãy chọn vấn đề gặp phải";

        String[] cacHienTuong = message.split(";");

        return hientuongService.giaiPhap(cacHienTuong);
    }


    private float BRM(int cangNang, int chieuCao, int old, int gender) {
        float calo;
        if (gender == 1) {
            calo = ((float) 9.99 * cangNang) + ((float) 6.25 * ((float) chieuCao / 100)) - ((float) 4.92 * old) + 5;

        } else {
            calo = ((float) 9.99 * cangNang) + ((float) 6.25 * ((float) chieuCao / 100)) - ((float) 4.92 * old) - 161;
        }
        return calo;
    }

}
