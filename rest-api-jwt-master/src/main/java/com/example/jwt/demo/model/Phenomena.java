package com.example.jwt.demo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;

@Entity
@Table(name = "phenomena")
@Getter
@Setter
public class Phenomena extends BaseEntity {
    private String problem;
    private String symptoms;
    private int relevance;

    @Transient
    private List<Solutions> solutions;

}
