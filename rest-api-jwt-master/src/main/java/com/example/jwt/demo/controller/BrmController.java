package com.example.jwt.demo.controller;

import com.example.jwt.demo.dto.BrmDto;
import com.example.jwt.demo.response.Response;
import com.example.jwt.demo.service.BrmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/Brm")
public class BrmController {
    @Autowired
    private BrmService service;
    @PostMapping("/ketQua")
    ResponseEntity<Response> BrmSave(@RequestBody BrmDto dto) {
        service.CaloOneDay(dto);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Response("SUSSES", dto, HttpStatus.OK));
    }
}
