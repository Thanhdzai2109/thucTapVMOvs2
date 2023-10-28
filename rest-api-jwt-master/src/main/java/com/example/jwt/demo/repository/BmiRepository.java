package com.example.jwt.demo.repository;

import com.example.jwt.demo.dto.BmiDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BmiRepository extends JpaRepository<BmiDto,Integer> {
}
