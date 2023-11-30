package com.example.jwt.demo.repository;

import com.example.jwt.demo.model.Phenomena;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HienTuongRepository extends JpaRepository<Phenomena, Long> {

    @Query(value = "select h from Phenomena h where h.symptoms like %?1%")
    List<Phenomena> findBySymptoms(String hienTuong);

}
