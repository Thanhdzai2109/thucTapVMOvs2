package com.example.jwt.demo.service.impl;

import com.example.jwt.demo.constant.GioiTinhConst;
import com.example.jwt.demo.dto.BmiDto;
import com.example.jwt.demo.dto.BrmDto;
import com.example.jwt.demo.dto.ThucDon;
import com.example.jwt.demo.model.Phenomena;
import com.example.jwt.demo.model.enums.GioiTinhEnum;
import com.example.jwt.demo.service.BmiSerVice;
import com.example.jwt.demo.service.ChatService;
import com.example.jwt.demo.service.PhenomenonService;
import com.example.jwt.demo.utils.Util;
import org.alicebot.ab.Chat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ChatServiceImpl implements ChatService {

    @Autowired
    private Chat chatSession;


    @Autowired
    private ThucDonService thucDonService;

    @Autowired
    private PhenomenonService hientuongService;

    @Autowired
    private BrmServiceImpl brmService;

    @Autowired
    BmiSerVice bmiSerVice;

    @Override
    public String getChatResponse(String message) {
        String response = chatSession.multisentenceRespond(message);
        String topic = chatSession.predicates.get("topic");
        if (!Objects.equals(topic, "unknown")) {

            int height = Util.tryParseInt(chatSession.predicates.get("user_height"));
            int weight = Util.tryParseInt(chatSession.predicates.get("user_weight"));
            int old = Util.tryParseInt(chatSession.predicates.get("user_age"));
            int month = Util.tryParseInt(chatSession.predicates.get("user_age"));
            int activityLevel = brmService.mapActivityLevel(chatSession.predicates.get("activity_level"));
            String hienTuong = chatSession.predicates.get("hien_tuong");
            int gender = 0;
            if (chatSession.predicates.get("user_gender").toLowerCase() == "unknown") {
                response = "Cho tôi biết giới tính của bạn";
            } else {
                if (chatSession.predicates.get("user_gender").toLowerCase().equals("nam")) {
                    gender = 1;
                } else {
                    gender = 0;
                }
            }

            switch (topic) {
                case "BMI":
                    if (height == 0) {
                        response = "Vui lòng cho tôi biết chiều cao của bạn (cm)";
                    }
                    else if (weight == 0) {
                        response = "Vui lòng cho tôi biết cân nặng của bạn (kg)";
                    }
                    else if (old == 0) {
                        response = "Vui lòng cho tôi biết tuổi của bạn";
                    }
                    else {
                        BmiDto dto = new BmiDto();
                        dto.setHeight(height);
                        dto.setWeight(weight);
                        dto.setSex(gender);
                        dto.setOld(old);
                        response = "Chỉ số BMI của bạn là: " + bmiSerVice.ketQua(dto) + " (" + bmiSerVice.checkBmi(dto)+")";
                    }
                    break;
                case "Kalo":
                    if (height == 0) {
                        response = "Vui lòng cho tôi biết chiều cao của bạn (cm)";
                    } else if (weight == 0) {
                        response = "Vui lòng cho tôi biết cân nặng của bạn (kg)";
                    } else if (old == 0) {
                        response = "Vui lòng cho tôi biết tuổi của Bạn";
                    } else {
                        BrmDto dto = new BrmDto();
                        dto.setHeight(height);
                        dto.setWeight(weight);
                        dto.setSex(gender);
                        dto.setOld(old);
                        response = "Bạn nên ăn " + brmService.UocTinhCalo(dto) + " một ngày";
                    }
                    break;
                case "weightgain":
                    if (height == 0) {
                        response = "Vui lòng cho tôi biết chiều cao của bạn (cm)";
                    } else if (weight == 0) {
                        response = "Vui lòng cho tôi biết cân nặng của bạn (kg)";
                    }
                    else if (old == 0) {
                        response = "Vui lòng cho tôi biết tuổi của Bạn";
                    }
                    else if (activityLevel == 0) {
                        response = "Vui lòng cho tôi biết mức độ vận động của bạn mấy lần trên tuần";
                    }
                    else {
                        BrmDto dto = new BrmDto();
                        dto.setHeight(height);
                        dto.setWeight(weight);
                        dto.setSex(gender);
                        dto.setOld(old);
                        dto.setActivityLevel(activityLevel);
                        response = "Bạn nên ăn " + brmService.CaloOneDay(dto) + " một ngày để có thể tăng cân";
                    }
                    break;
                case "bodymenu":
                    if (height == 0) {
                        response = "Vui lòng cho tôi biết chiều cao của trẻ (cm)";
                    } else if (weight == 0) {
                        response = "Vui lòng cho tôi biết cân nặng của trẻ (kg)";
                    } else if (month == 0) {
                        response = "Vui lòng cho tôi biết tuổi của trẻ (Tháng tuổi)";
                    } else {
                        response = thucDonTheoTheTrang(chatSession.predicates.get("user_gender"), month, height, weight).getMessage();
                    }
                    break;
                case "hientuongmenu":
                    if(hienTuong.equals("unknown")) {
                        List<Phenomena> list = hientuongService.getHienTuongs();
                        response = "Hãy chọn biểu hiện của trẻ: \n";
                        response += list.stream().map(item -> item.getSymptoms()).collect(Collectors.joining(";"));
                    }else {
                        response = thucDonTheoHienTuong(message);
                    }
                    break;
                default:
                    break;
            }
        }
        return response;
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
