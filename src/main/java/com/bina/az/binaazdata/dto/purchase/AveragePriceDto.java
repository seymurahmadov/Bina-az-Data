package com.bina.az.binaazdata.dto.purchase;

import lombok.Data;

import java.util.Date;

@Data
public class AveragePriceDto {
    String location;
    String rooms;
    String extract;
    String repair;
    String floor;
//    Long firstPrice;
//    Long lastPrice;
    private Date dateFrom;
}
