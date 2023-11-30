package com.example.jwt.demo.repository;

import com.example.jwt.demo.model.FoodKalo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ThucPhamChuyenDoiRepository extends JpaRepository<FoodKalo, Long> {

    List<FoodKalo> findByGroup(String nhom);
}
