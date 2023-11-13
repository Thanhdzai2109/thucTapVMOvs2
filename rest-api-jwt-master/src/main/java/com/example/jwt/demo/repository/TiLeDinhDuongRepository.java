package com.example.jwt.demo.repository;

import com.example.jwt.demo.model.TileDinhDuong;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TiLeDinhDuongRepository extends JpaRepository<TileDinhDuong, Long> {
    @Query(value = "select t from TileDinhDuong t where t.tuoiToiThieu < ?1 and t.tuoiToiDa >= ?1")
    TileDinhDuong findByTuoi(double tuoi);
}
