package com.example.jwt.demo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "solutions")
@Getter
@Setter
public class Solutions extends BaseEntity {
    private String solution;
    private String problem;
}
