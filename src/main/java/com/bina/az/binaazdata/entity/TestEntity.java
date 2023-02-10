package com.bina.az.binaazdata.entity;

import lombok.Data;
import org.springframework.scheduling.annotation.Scheduled;

import javax.persistence.*;

@Entity
@Table(name="Test")
public class TestEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String name;
}
