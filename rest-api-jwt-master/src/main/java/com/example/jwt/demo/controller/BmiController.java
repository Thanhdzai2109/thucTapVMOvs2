package com.example.jwt.demo.controller;

import com.example.jwt.demo.dto.BmiDto;
import com.example.jwt.demo.response.Response;
import com.example.jwt.demo.service.BmiSerVice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/bmi")
public class BmiController {
    @Autowired
    private BmiSerVice serVice;

    @PostMapping("/ketQua")
    ResponseEntity<Response> AddTrungTam(@RequestBody BmiDto dto) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Response(serVice.checkBmi(dto), dto, HttpStatus.OK));
    }
}
