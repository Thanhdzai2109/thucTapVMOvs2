package com.example.jwt.demo.repository;

import com.example.jwt.demo.model.HeightWeight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ChieuCaoCanNangRepository extends JpaRepository<HeightWeight, Long> {

    @Query(value = "select c from HeightWeight c where c.age = ?1 and c.gender = ?2")
    HeightWeight findByTuoiAndGioiTinh(double tuoi, String gioiTinh);
}
