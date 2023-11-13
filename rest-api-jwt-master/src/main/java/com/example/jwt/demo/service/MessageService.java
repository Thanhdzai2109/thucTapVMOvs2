package com.example.jwt.demo.service;

import com.example.jwt.demo.dto.ThucDon;
import com.example.jwt.demo.dto.Tre;

public interface MessageService{
    ThucDon thucDonTheoTheTrang(Tre tre);
    String thucDonTheoHienTuong(String message);
}
