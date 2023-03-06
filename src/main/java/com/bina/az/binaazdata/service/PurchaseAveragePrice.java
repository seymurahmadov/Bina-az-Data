package com.bina.az.binaazdata.service;

import com.bina.az.binaazdata.Jsoup.purchase.JsoupPurchaseNewBuilding;
import com.bina.az.binaazdata.dto.purchase.PurchaseNewBuildingDto;
import com.bina.az.binaazdata.entity.PurchaseNewBuildingEntity;
import com.bina.az.binaazdata.repository.PurchaseNewBuildingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;

@RequiredArgsConstructor
@Service
public class PurchaseAveragePrice {
    private final JsoupPurchaseNewBuilding jsoupPurchaseNewBuilding;
    private final PurchaseNewBuildingRepository repository;

    Long sumOfPrice;
    Double averagePrice;
//
//
//    public PurchaseNewBuildingEntity averagePrice(PurchaseNewBuildingEntity entity) throws IOException {
//        PurchaseNewBuildingDto dto = jsoupPurchaseNewBuilding.purchaseJsoupNewBuildingData();
//
//        PurchaseNewBuildingEntity entity1 = PurchaseNewBuildingEntity.builder()
//                .location(dto.getLocation())
//                .price(dto.getPrice())
//                .build();
//
//         return repository.save(entity);
//
//    public PurchaseNewBuildingEntity averagePriceNew() throws IOException {
//        Long price = Long.valueOf(jsoupPurchaseNewBuilding.purchaseJsoupNewBuildingData().getPrice());
//        sumOfPrice+= price;
//        averagePrice = Double.valueOf(sumOfPrice / jsoupPurchaseNewBuilding.purchaseJsoupNewBuildingData().getId());
//
//
//        return null;

    public Double setAveragePrice() throws IOException {
        PurchaseNewBuildingDto dto = jsoupPurchaseNewBuilding.purchaseJsoupNewBuildingData();



        PurchaseNewBuildingEntity newBuildingEntity = PurchaseNewBuildingEntity.builder()
             .price(dto.getPrice())
             .location(dto.getLocation())
             .build();


        repository.save(newBuildingEntity);



        return averagePrice;


    }











    }


