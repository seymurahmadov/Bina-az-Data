package com.bina.az.binaazdata.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@Table(name = "purchase")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PurchaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "announcement_id")
    private int announcementId;

    @Column(name = "price")
    private int price;

    @Column(name = "location")
    private String location;

    @Column(name = "extract")
    private String extract;

    @Column(name = "repair")
    private String repair;

    @Column(name = "rooms")
    private int rooms;

    @Column(name = "count_of_floor")
    private int countOfFloor;

    @Column(name = "square_meter/sot")
    private int squareMeter;

    @Column(name = "category")
    private String category;



}
