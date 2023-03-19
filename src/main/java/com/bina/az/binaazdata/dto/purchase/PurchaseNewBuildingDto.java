package com.bina.az.binaazdata.dto.purchase;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;


@Data
public class PurchaseNewBuildingDto {
    private int id;

    private int announcementId; //+

    private String price; //+

    private String location; //+

    private String extract; //+

    private String repair; //+

    private String rooms; //+

    private String countOfFloor;    //+

    private String area;  //+

    private String category; //+

    private String latitude; //+

    private String longitude; //+

    private Date date;



}
