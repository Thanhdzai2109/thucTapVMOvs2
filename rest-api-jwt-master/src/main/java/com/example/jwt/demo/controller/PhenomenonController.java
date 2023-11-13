package com.example.jwt.demo.controller;

import com.example.jwt.demo.model.HienTuong;
import com.example.jwt.demo.service.impl.PhenomenonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/phenomenon")
@CrossOrigin("*")
public class PhenomenonController {

    @Autowired
    private PhenomenonServiceImpl hienTuongService;

    @GetMapping
    public List<HienTuong> getHienTuongs() {
        return hienTuongService.getHienTuongs();
    }
}
