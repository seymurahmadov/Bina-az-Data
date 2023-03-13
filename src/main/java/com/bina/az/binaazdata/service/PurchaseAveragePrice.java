package com.bina.az.binaazdata.service;

import com.bina.az.binaazdata.JsoupService.purchase.JsoupPurchaseNewBuilding;
import com.bina.az.binaazdata.entity.PurchaseNewBuildingEntity;
import com.bina.az.binaazdata.repository.PurchaseNewBuildingRepository;
import io.swagger.annotations.ApiModelProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;

@RequiredArgsConstructor
@Service
public class PurchaseAveragePrice {

    private final PurchaseNewBuildingRepository repository;


    Long averagePrice =0L;





    public Long setAveragePrice(String location,String rooms) throws IOException {

        ArrayList<PurchaseNewBuildingEntity> allByLocation = repository.findAllByLocationAndAndRooms(location,rooms);

        for (PurchaseNewBuildingEntity e : allByLocation){
            long l =Long.parseLong(e.getPrice().replaceAll(" ",""));
            averagePrice+=l;

        }
        averagePrice = averagePrice/allByLocation.size();

        return averagePrice;

    }











    }


