package com.example.jwt.demo.controller;

import com.example.jwt.demo.dto.ThucDon;
import com.example.jwt.demo.dto.Tre;
import com.example.jwt.demo.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @PostMapping("/physical-menu")
    public ResponseEntity<ThucDon> kichBan1(@RequestBody Tre tre) {

        return ResponseEntity.ok(messageService.thucDonTheoTheTrang(tre));
    }

    @PostMapping("/phenomenal-menu")
    public ResponseEntity<ThucDon> kichBan2(@RequestParam String hienTuong) {
        ThucDon thucDon = new ThucDon();
        thucDon.setMessage(messageService.thucDonTheoHienTuong(hienTuong));
        return ResponseEntity.ok(thucDon);
    }
}
