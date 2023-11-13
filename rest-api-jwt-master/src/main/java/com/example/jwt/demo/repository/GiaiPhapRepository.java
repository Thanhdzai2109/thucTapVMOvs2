package com.example.jwt.demo.repository;

import com.example.jwt.demo.model.GiaiPhap;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GiaiPhapRepository extends JpaRepository<GiaiPhap, Long> {

    List<GiaiPhap> findByVanDe(String vanDe);
}
