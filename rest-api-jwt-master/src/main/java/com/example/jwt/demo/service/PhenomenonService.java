package com.example.jwt.demo.service;

import com.example.jwt.demo.model.Phenomena;

import java.util.List;

public interface PhenomenonService {
    List<Phenomena> getHienTuongs();
    String giaiPhap(String[] hienTuongs);
}
