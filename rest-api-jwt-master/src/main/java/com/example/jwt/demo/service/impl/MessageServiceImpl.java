package com.example.jwt.demo.service.impl;

import com.example.jwt.demo.constant.GioiTinhConst;
import com.example.jwt.demo.dto.ThucDon;
import com.example.jwt.demo.dto.Tre;
import com.example.jwt.demo.model.enums.GioiTinhEnum;
import com.example.jwt.demo.service.MessageService;
import com.example.jwt.demo.service.PhenomenonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private PhenomenonService phenomenonService;

    @Autowired
    private ThucDonService thucDonService;

    // kịch bản 1 : Tư vấn chế độ theo thể trạng
    public ThucDon thucDonTheoTheTrang(Tre tre) {
        double height = tre.getHeight(), weight = tre.getWeight();
        double month = tre.getOld();
        String sex= "";
        if(tre.getGender() == GioiTinhEnum.Nam){
            sex = GioiTinhConst.nam;
        }else {
            sex = GioiTinhConst.nu;
        }
        if (month < 24 || month > 60
            || height <= 0 || weight <= 0){
            ThucDon thucDon = new ThucDon();
            thucDon.setMessage("Dữ liệu không hợp lệ");
            return thucDon;
        }


        return thucDonService.thucDonDinhDuong(sex, month, weight, height);
    }

    // kịch bản 2: Chế độ ăn theo hiện tượng
    public String thucDonTheoHienTuong(String message) {

        if(message.equals("")) return "Hãy chọn vấn đề gặp phải";

        String[] cacHienTuong = message.split(";");

        return phenomenonService.giaiPhap(cacHienTuong);
    }

}
