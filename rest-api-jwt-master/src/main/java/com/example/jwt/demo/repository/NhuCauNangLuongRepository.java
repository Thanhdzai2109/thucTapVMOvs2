package com.example.jwt.demo.repository;

import com.example.jwt.demo.model.KaloNeeds;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface NhuCauNangLuongRepository extends JpaRepository<KaloNeeds, Long> {

    @Query(value = "select n from KaloNeeds n where n.minAge < ?1 and n.maxAge >= ?1")
    KaloNeeds findByAge(double thang);
}
