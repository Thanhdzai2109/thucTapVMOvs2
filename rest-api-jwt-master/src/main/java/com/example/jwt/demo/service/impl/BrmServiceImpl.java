package com.example.jwt.demo.service.impl;

import com.example.jwt.demo.dto.BrmDto;
import com.example.jwt.demo.repository.BrmRepository;
import com.example.jwt.demo.service.BrmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BrmServiceImpl implements BrmService {
    @Autowired
    private BrmRepository repo;


    @Override
    public float CaloOneDay(BrmDto dto) {
        float brm;
        float TDEE;
        if (dto.getSex() == 1) {
            brm = ((float) 9.99 * dto.getWeight()) + ((float) 6.25 * ((float) dto.getHeight() / 100)) - ((float) 4.92 * dto.getOld()) + 5;
            if (dto.getActivityLevel() == 1) {
                TDEE = brm * 1.2f;
            } else if (dto.getActivityLevel() == 2) {
                TDEE = brm * 1.375f;
            } else {
                TDEE = brm * 1.55f;
            }
        } else {
            brm = ((float) 9.99 * dto.getWeight()) + ((float) 6.25 * ((float) dto.getHeight() / 100)) - ((float) 4.92 * dto.getOld()) - 161;
            if (dto.getActivityLevel() == 1) {
                TDEE = brm * 1.2f;
            } else if (dto.getActivityLevel() == 2) {
                TDEE = brm * 1.375f;
            } else {
                TDEE = brm * 1.55f;
            }
        }
        dto.setKetQua(TDEE);
        repo.save(dto);

        return TDEE;
    }

    @Override
    public float UocTinhCalo(BrmDto dto) {
        float calo;
        if (dto.getSex() == 1) {
            calo = ((float) 9.99 * dto.getWeight()) + ((float) 6.25 * ((float) dto.getHeight() / 100)) - ((float) 4.92 * dto.getOld()) + 5;

        } else {
            calo = ((float) 9.99 * dto.getWeight()) + ((float) 6.25 * ((float) dto.getHeight() / 100)) - ((float) 4.92 * dto.getOld()) - 161;
        }
        dto.setKetQua(calo);
        repo.save(dto);
        return calo;
    }

    @Override
    public int mapActivityLevel(String value) {
        if(value.isEmpty() || value.equals("unknown")) return 0;
        if(value.contains("1") && value.contains("2")){
            return 1;
        }

        if(value.contains("2") && value.contains("3")){
            return 2;
        }

        if(value.contains("1")) return 1;

        if(value.contains("2")) return 2;

        return 3;
    }


}
