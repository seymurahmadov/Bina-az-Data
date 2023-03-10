package com.bina.az.binaazdata.service;

import com.bina.az.binaazdata.JsoupService.purchase.JsoupPurchaseNewBuilding;
import com.bina.az.binaazdata.dto.purchase.PurchaseNewBuildingDto;
import com.bina.az.binaazdata.entity.PurchaseNewBuildingEntity;
import com.bina.az.binaazdata.repository.PurchaseNewBuildingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;

@RequiredArgsConstructor
@Service
public class PurchaseAveragePrice {
    private final JsoupPurchaseNewBuilding jsoupPurchaseNewBuilding;
    private final PurchaseNewBuildingRepository repository;


//    Double averagePrice=0.0;
//
//
//    public Double setAveragePrice(String location) throws IOException {
//
//        ArrayList<PurchaseNewBuildingEntity> byLocationanAndPrice = repository.findByLocationanAndPrice(location);
//
//
//        for (PurchaseNewBuildingEntity e : byLocationanAndPrice){
//            averagePrice+=averagePrice;
//        }
//        return averagePrice=averagePrice/byLocationanAndPrice.size();
//
//    }











    }


