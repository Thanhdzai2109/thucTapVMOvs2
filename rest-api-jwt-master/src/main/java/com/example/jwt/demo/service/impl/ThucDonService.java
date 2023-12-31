package com.example.jwt.demo.service.impl;

import com.example.jwt.demo.dto.ThucDon;
import com.example.jwt.demo.model.ThucPhamChuyenDoi;
import com.example.jwt.demo.model.TileDinhDuong;
import com.example.jwt.demo.repository.NhuCauNangLuongRepository;
import com.example.jwt.demo.repository.ThucPhamChuyenDoiRepository;
import com.example.jwt.demo.repository.TiLeDinhDuongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class ThucDonService {

    @Autowired
    private FuzzyLogic fuzzyLogic;

    @Autowired
    private ThucPhamChuyenDoiRepository thucPhamChuyenDoiRepository;

    @Autowired
    private NhuCauNangLuongRepository nhuCauNangLuongRepository;

    @Autowired
    private TiLeDinhDuongRepository tiLeDinhDuongRepository;


    public ThucDon thucDonDinhDuong(String sex, double month, double weight, double height) {
//        Dựa vào các thông số tìm ra tình trạng của trẻ: Suy dinh dưỡng, bình thường, thừa cân, béo phì
        String theTrang = fuzzyLogic.xacDinhTheTrang(sex, month, weight, height);

//        Lấy ra nhu cầu năng lượng theo độ tuổi từ DB đơn vị kcal
        double kcal = nhuCauNangLuongRepository.findByThang(month).getNangLuong();
        switch (theTrang) {
            case "Suy dinh dưỡng cấp 2":
                kcal = kcal * 1.2;
                break;
            case "Suy dinh dưỡng cấp 1":
                kcal = kcal * 1.1;
                break;
            case "Thừa cân":
                kcal = kcal * 0.9;
                break;
            case "Béo phì":
                kcal = kcal * 0.8;
                break;
        }


        TileDinhDuong tileDinhDuong = tiLeDinhDuongRepository.findByTuoi(month);
        double tinhBot = kcal * (tileDinhDuong.getTinhBot() / 100);
        double chatBeo = kcal * (tileDinhDuong.getChatBeo() / 100);
        double protein = kcal * (tileDinhDuong.getProtein() / 100);

        Random Rand = new Random(); // random thuc pham
        StringBuilder tb = new StringBuilder(); // random tinh bot
        List<ThucPhamChuyenDoi> listTb = thucPhamChuyenDoiRepository.findByNhom("Nhiều tinh bột");

        for (int i = 1; i <= 3; i++) {
            int index = Rand.nextInt(listTb.size());
            ThucPhamChuyenDoi thucPhamChuyenDoi = listTb.remove(index);
            tb.append(thucPhamChuyenDoi.getThucPham());
            tb.append(',');
        }

        StringBuilder cb = new StringBuilder(); // random chat beo
        List<ThucPhamChuyenDoi> listCb = thucPhamChuyenDoiRepository.findByNhom("Nhiều chất béo");

        for (int i = 1; i <= 3; i++) {
            int index = Rand.nextInt(listCb.size());
            ThucPhamChuyenDoi thucPhamChuyenDoi = listCb.remove(index);
            cb.append(thucPhamChuyenDoi.getThucPham());
            cb.append(',');
        }

        StringBuilder pr = new StringBuilder(); // random protein
        List<ThucPhamChuyenDoi> listPr = thucPhamChuyenDoiRepository.findByNhom("Nhiều protein");
        for (int i = 1; i <= 3; i++) {
            int index = Rand.nextInt(listPr.size());
            ThucPhamChuyenDoi thucPhamChuyenDoi = listPr.remove(index);
            pr.append(thucPhamChuyenDoi.getThucPham());
            pr.append(',');
        }

        String message = "Thể trạng hiện tại của trẻ là: " + theTrang + "\n" +
                "Khẩu phần ăn phù hợp cho trẻ là khoảng: " + kcal + " kcal\n" +
                "Thực đơn ví dụ như:\n" +
                "- " + tinhBot + " kcal từ " + tb + "\n" +
                "- " + chatBeo + " kcal từ " + cb + "\n" +
                "- " + protein + " kcal từ " + pr + "\n";

        ThucDon thucDon = new ThucDon();
        thucDon.setMessage(message);

        return thucDon;
    }

}
