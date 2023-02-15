package com.bina.az.binaazdata.dto.purchase;


import lombok.Data;

@Data
public class PurchaseOldBuildingDto {
    private int id;

    private int announcementId; //+

    private int price; //+

    private String location; //+

    private String extract; //+

    private String repair; //+

    private int rooms; //+

    private int countOfFloor;    //+

    private int area;

    private String category; //+

    private String latitude; //+

    private String longitude; //+

}
