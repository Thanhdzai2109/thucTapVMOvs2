package com.example.jwt.demo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "food_kalo")
@Getter
@Setter
public class FoodKalo extends BaseEntity {
    private String name;
    private String group;
    private double kcal;
    private String starch;
    private String fatRatio;
    private String protein;
    private double fiber;

    public String getThucPham(){
        return name +"("+kcal+"/"+"100g"+")";
    }
}
