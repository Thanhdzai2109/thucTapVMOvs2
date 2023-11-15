package com.example.jwt.demo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "giai_phap")
@Getter
@Setter
public class GiaiPhap extends BaseEntity {
    private String giaiPhap;
    private String vanDe;
}
