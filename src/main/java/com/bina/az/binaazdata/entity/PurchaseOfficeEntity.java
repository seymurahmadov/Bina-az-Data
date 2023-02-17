package com.bina.az.binaazdata.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    private int price; //+

    private String location; //+

    private String extract; //+

    private String repair; //+

    private int rooms; //+

    private String typeOfBuilding;  //+

    private int area;  //+

    private String category; //+

    private String latitude; //+

    private String longitude; //+
}
