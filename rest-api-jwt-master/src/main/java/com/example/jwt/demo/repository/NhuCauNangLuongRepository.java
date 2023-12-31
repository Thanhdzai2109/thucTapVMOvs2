package com.example.jwt.demo.repository;

import com.example.jwt.demo.model.NhuCauNangLuong;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface NhuCauNangLuongRepository extends JpaRepository<NhuCauNangLuong, Long> {

    @Query(value = "select n from NhuCauNangLuong n where n.tuoiToiThieu < ?1 and n.tuoiToiDa >= ?1")
    NhuCauNangLuong findByThang(double thang);
}
