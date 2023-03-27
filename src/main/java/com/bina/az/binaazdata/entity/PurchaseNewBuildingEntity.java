package com.bina.az.binaazdata.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "purchase_new_building")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PurchaseNewBuildingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "announcement_id")
    private int announcementId;

    @Column(name = "price")
    private String price;

    @Column(name = "location")
    private String location;

    @Column(name = "extract")
    private String extract;

    @Column(name = "repair")
    private String repair;

    @Column(name = "rooms")
    private String rooms;

    @Column(name = "count_of_floor")
    private String countOfFloor;

    @Column(name = "area")
    private String area;

    @Column(name = "category")
    private String category;

    private String latitude; //+

    private String longitude; //+

    private Date date;



}
