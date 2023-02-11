package com.bina.az.binaazdata.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.scheduling.annotation.Scheduled;


@Entity
@Table(name="Test")
public class TestEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String name;
}
