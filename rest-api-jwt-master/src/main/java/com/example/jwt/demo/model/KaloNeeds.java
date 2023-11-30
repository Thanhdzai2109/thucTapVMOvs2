package com.example.jwt.demo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "kalo_needs")
@Getter
@Setter
public class KaloNeeds extends BaseEntity {
    // tính theo tháng
    private double kalo;
    @Column(name = "minAge")
    private double minAge;
    @Column(name = "maxAge")
    private double maxAge;

}
