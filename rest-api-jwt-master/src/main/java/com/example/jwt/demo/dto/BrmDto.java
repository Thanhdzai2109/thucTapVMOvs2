package com.example.jwt.demo.dto;

import javax.persistence.*;

@Entity
@Table(name = "Brm")
public class BrmDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "height")
    private int height;
    @Column(name = "weight")
    private float weight;
    @Column(name = "old")
    private int old;
    @Column(name = "sex")
    private int sex;
    @Column(name = "activity_level")
    private int activityLevel;
    @Column(name = "target_weight")
    private float targetWeight;
    @Column(name = "weight_gain")
    private int weightGain;
    @Column(name = "ket_qua")
    private float ketQua;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getActivityLevel() {
        return activityLevel;
    }

    public void setActivityLevel(int activityLevel) {
        this.activityLevel = activityLevel;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getOld() {
        return old;
    }

    public void setOld(int old) {
        this.old = old;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public float getTargetWeight() {
        return targetWeight;
    }

    public void setTargetWeight(float targetWeight) {
        this.targetWeight = targetWeight;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public int getWeightGain() {
        return weightGain;
    }

    public void setWeightGain(int weightGain) {
        this.weightGain = weightGain;
    }

    public float getKetQua() {
        return ketQua;
    }

    public void setKetQua(float ketQua) {
        this.ketQua = ketQua;
    }
}
