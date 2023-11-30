package com.example.jwt.demo.service.impl;

import com.example.jwt.demo.dto.ThucDon;
import com.example.jwt.demo.model.Solutions;
import com.example.jwt.demo.model.Phenomena;
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

    public List<Phenomena> getHienTuongs() {
        return hienTuongRepository.findAll();
    }

    public String giaiPhap(String[] hienTuongs) {
        List<Phenomena> hienTuongList = new ArrayList<>();
        for (String hienTuong : hienTuongs) {
            hienTuongList.addAll(hienTuongRepository.findBySymptoms(hienTuong.trim()));
        }

        Map<String, Integer> map = new HashMap<>();
        int doPhuHopMax = 0;
        String vanDe = "";

        // Tìm vấn đề có độ phù hợp cao nhất
        for (Phenomena hienTuong : hienTuongList) {

            map.putIfAbsent(hienTuong.getProblem(), 0);

            Integer mucDoPhuHop = map.get(hienTuong.getProblem());
            map.put(hienTuong.getProblem(), mucDoPhuHop + hienTuong.getRelevance());
            if (map.get(hienTuong.getProblem()) > doPhuHopMax) {
                doPhuHopMax = map.get(hienTuong.getProblem());
                vanDe = hienTuong.getProblem();
            }
        }

        if (vanDe.isEmpty())
            return "Không thể tư vấn từ các hiện tượng trên";

        StringBuilder result = new StringBuilder("Từ hiện tượng, có thể trẻ đang gặp vấn đề ");
        result.append(vanDe.toLowerCase()).append(", giải pháp cho vấn đề này là");
        List<Solutions> solutionList = giaiPhapRepository.findByProblem(vanDe);
        for (Solutions solution : solutionList) {
            result.append("\n- ").append(solution.getSolution());
        }
        ThucDon thucDon = new ThucDon();
        thucDon.setMessage(result.toString());
        return result.toString();
    }
}
