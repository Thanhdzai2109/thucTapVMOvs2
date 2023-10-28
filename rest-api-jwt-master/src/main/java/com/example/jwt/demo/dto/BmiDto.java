package com.example.jwt.demo.dto;

import javax.persistence.*;

@Entity
@Table(name="Bmi")
public class BmiDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="height")
    private int height;
    @Column(name="weight")
    private float weight;
    @Column(name="old")
    private int old;
    @Column(name="sex")
    private int sex;
    @Column(name="ket_qua")
    private float ketQua;

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public float getKetQua() {
        return ketQua;
    }

    public void setKetQua(float ketQua) {
        this.ketQua = ketQua;
    }
}
