package com.example.jwt.demo.repository;

import com.example.jwt.demo.dto.BrmDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrmRepository extends JpaRepository<BrmDto, Integer> {

}
