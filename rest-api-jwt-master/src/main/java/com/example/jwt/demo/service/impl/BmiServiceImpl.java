package com.example.jwt.demo.service.impl;

import com.example.jwt.demo.dto.BmiDto;
import com.example.jwt.demo.repository.BmiRepository;
import com.example.jwt.demo.service.BmiSerVice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BmiServiceImpl implements BmiSerVice {
    @Autowired
    private BmiRepository repo;

    @Override
    public String checkBmi(BmiDto dto) {
        dto.setKetQua(ketQua(dto));
        repo.save(dto);
        if (dto.getOld() > 16) {
            if (ketQua(dto) < 18.5) {
                return "Gầy";
            } else if (ketQua(dto) >= 25) {
                return "Thừa cân";
            }
            return "Cân đối";
        } else {
            if (ketQua(dto) < 14) {
                return "Gầy";
            } else if (ketQua(dto) >= 20) {
                return "Thừa cân";
            }
            return "Cân đối";
        }
    }

    @Override
    public float ketQua(BmiDto dto) {
        float ketQua = dto.getWeight() / (dto.getHeight()/100);
        return ketQua;
    }
}
