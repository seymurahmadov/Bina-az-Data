package com.bina.az.binaazdata.dto.purchase;

import lombok.Data;

import java.util.Date;


@Data
public class PurchaseNewBuildingDto {
    private int id;

    private int announcementId; //+

    private Long price; //+

    private String location; //+

    private String extract; //+

    private String repair; //+

    private String rooms; //+

    private String countOfFloor;    //+

    private Integer area;  //+

    private String category; //+

    private String latitude; //+

    private String longitude; //+

    private Date date;



}
