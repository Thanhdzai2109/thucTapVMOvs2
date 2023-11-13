package com.example.jwt.demo.repository;

import com.example.jwt.demo.model.ThucPhamChuyenDoi;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ThucPhamChuyenDoiRepository extends JpaRepository<ThucPhamChuyenDoi, Long> {

    List<ThucPhamChuyenDoi> findByNhom(String nhom);
}
