package com.example.jwt.demo.repository;

import com.example.jwt.demo.model.Solutions;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GiaiPhapRepository extends JpaRepository<Solutions, Long> {

    List<Solutions> findByProblem(String vanDe);
}
