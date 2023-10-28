package com.example.jwt.demo.service;

import com.example.jwt.demo.dto.BmiDto;

public interface BmiSerVice {
    String checkBmi (BmiDto dto);
    float ketQua(BmiDto dto);
}
