package com.bina.az.binaazdata.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "purchase_land")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PurchaseLandEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int announcementId; //+

    private String price; //+

    private String location; //+

    private String extract; //+

    private String landArea; //+

    private String category; //+

    private String latitude; //+

    private String longitude; //+

    private String date;
}
