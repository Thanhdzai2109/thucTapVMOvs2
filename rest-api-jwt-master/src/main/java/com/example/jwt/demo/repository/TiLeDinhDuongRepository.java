package com.example.jwt.demo.repository;

import com.example.jwt.demo.model.NutritionalRatio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TiLeDinhDuongRepository extends JpaRepository<NutritionalRatio, Long> {
    @Query(value = "select t from NutritionalRatio t where t.minAge < ?1 and t.maxAge >= ?1")
    NutritionalRatio findByAge(double tuoi);
}
