package com.example.jwt.demo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "nutritional_ratio")
@Getter
@Setter
public class NutritionalRatio extends BaseEntity {
    @Column(name = "min_age")
    private double minAge;
    @Column(name = "max_age")
    private double maxAge;
    @Column(name = "kcal")
    private double kcal;
    private double fat;
    private double starch;
    private double protein;
}
