package com.example.jwt.demo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "ti_le_dinh_duong")
@Getter
@Setter
public class TileDinhDuong extends BaseEntity {
    private double tuoiToiThieu;
    private double tuoiToiDa;
    @Column(name = "tong_kcal")
    private double tongKcal;
    private double chatBeo;
    private double tinhBot;
    private double protein;
}
