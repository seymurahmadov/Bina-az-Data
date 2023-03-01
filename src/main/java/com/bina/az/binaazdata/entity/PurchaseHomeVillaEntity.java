package com.bina.az.binaazdata.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "purchase_home_villa")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PurchaseHomeVillaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int announcementId; //+

    private int price; //+

    private String location; //+

    private String extract; //+

    private String repair; //+

    private int rooms; //+

    private int homeArea; //+

    private int landArea; //+

    private String category; //+

    private String latitude; //+

    private String longitude; //+
}
