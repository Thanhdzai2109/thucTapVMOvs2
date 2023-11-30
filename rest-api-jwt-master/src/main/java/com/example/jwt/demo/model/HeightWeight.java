package com.example.jwt.demo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "height_weight")
@Getter
@Setter
public class HeightWeight extends BaseEntity {
    private double age;
    private double maxWeight;
    private double minWeight;
    private double maxHeight;
    private double minHeight;
    private String gender;
}
