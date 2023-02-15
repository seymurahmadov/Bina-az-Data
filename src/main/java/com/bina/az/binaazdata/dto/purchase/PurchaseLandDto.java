package com.bina.az.binaazdata.dto.purchase;

import lombok.Data;

@Data
public class PurchaseLandDto {
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
