package com.bina.az.binaazdata.dto;

import lombok.Builder;
import lombok.Data;


@Data
public class PurchaseDto {
    private int id;

    private int announcementId;

    private int price;

    private String location;

    private String extract;

    private String repair;

    private int rooms;

    private int countOfFloor;

    private int squareMeter;

    private int sot;

    private String category;
}
