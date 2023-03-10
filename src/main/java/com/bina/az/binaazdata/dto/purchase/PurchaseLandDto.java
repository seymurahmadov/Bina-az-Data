package com.bina.az.binaazdata.dto.purchase;

import lombok.Data;

@Data
public class PurchaseLandDto {
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
