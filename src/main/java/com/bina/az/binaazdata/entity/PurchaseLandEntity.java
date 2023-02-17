package com.bina.az.binaazdata.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    private int price; //+

    private String location; //+

    private String extract; //+

    private int landArea; //+

    private String category; //+

    private String latitude; //+

    private String longitude; //+
}
