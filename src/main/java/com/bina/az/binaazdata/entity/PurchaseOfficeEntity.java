package com.bina.az.binaazdata.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Table(name = "purchase_office")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PurchaseOfficeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int announcementId; //+

    private String price; //+

    private String location; //+

    private String extract; //+

    private String repair; //+

    private String rooms; //+

    private String typeOfBuilding;  //+

    private String area;  //+

    private String category; //+

    private String latitude; //+

    private String longitude; //+

    private String date;
}
