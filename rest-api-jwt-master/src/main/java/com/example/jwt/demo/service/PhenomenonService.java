package com.example.jwt.demo.service;

import com.example.jwt.demo.model.HienTuong;

import java.util.List;

public interface PhenomenonService {
    List<HienTuong> getHienTuongs();
    String giaiPhap(String[] hienTuongs);
}
