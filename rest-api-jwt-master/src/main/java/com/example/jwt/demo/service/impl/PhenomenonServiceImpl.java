package com.example.jwt.demo.service.impl;

import com.example.jwt.demo.dto.ThucDon;
import com.example.jwt.demo.model.GiaiPhap;
import com.example.jwt.demo.model.HienTuong;
import com.example.jwt.demo.repository.GiaiPhapRepository;
import com.example.jwt.demo.repository.HienTuongRepository;
import com.example.jwt.demo.service.PhenomenonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PhenomenonServiceImpl implements PhenomenonService {

    @Autowired
    private HienTuongRepository hienTuongRepository;

    @Autowired
    private GiaiPhapRepository giaiPhapRepository;

    public List<HienTuong> getHienTuongs() {
        return hienTuongRepository.findAll();
    }

    public String giaiPhap(String[] hienTuongs) {
        List<HienTuong> hienTuongList = new ArrayList<>();
        for (String hienTuong : hienTuongs) {
            hienTuongList.addAll(hienTuongRepository.findByHienTuong(hienTuong.trim()));
        }

        Map<String, Integer> map = new HashMap<>();
        int doPhuHopMax = 0;
        String vanDe = "";

        // Tìm vấn đề có độ phù hợp cao nhất
        for (HienTuong hienTuong : hienTuongList) {

            map.putIfAbsent(hienTuong.getVanDe(), 0);

            Integer mucDoPhuHop = map.get(hienTuong.getVanDe());
            map.put(hienTuong.getVanDe(), mucDoPhuHop + hienTuong.getMucDoPhuHop());
            if (map.get(hienTuong.getVanDe()) > doPhuHopMax) {
                doPhuHopMax = map.get(hienTuong.getVanDe());
                vanDe = hienTuong.getVanDe();
            }
        }

        if (vanDe.isEmpty())
            return "Không thể tư vấn từ các hiện tượng trên";

        StringBuilder result = new StringBuilder("Từ hiện tượng, có thể trẻ đang gặp vấn đề ");
        result.append(vanDe.toLowerCase()).append(", giải pháp cho vấn đề này là");
        List<GiaiPhap> giaiPhapList = giaiPhapRepository.findByVanDe(vanDe);
        for (GiaiPhap giaiPhap : giaiPhapList) {
            result.append("\n- ").append(giaiPhap.getGiaiPhap());
        }
        ThucDon thucDon = new ThucDon();
        thucDon.setMessage(result.toString());
        return result.toString();
    }
}
