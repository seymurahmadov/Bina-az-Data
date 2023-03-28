package com.bina.az.binaazdata.service;

import com.bina.az.binaazdata.JsoupService.purchase.JsoupPurchaseNewBuilding;
import com.bina.az.binaazdata.repository.PurchaseNewBuildingRepository;
import lombok.RequiredArgsConstructor;

import java.io.IOException;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class Service {

private final PurchaseNewBuildingRepository purchaseNewBuildingRepository;
private final JsoupPurchaseNewBuilding jsoupPurchaseNewBuildingService;




    public void saveNewBuildingJsoupData() throws IOException {

//        PurchaseNewBuildingDto purchaseNewBuildingDto = jsoupPurchaseNewBuildingService.purchaseJsoupNewBuildingData();
//
//        PurchaseNewBuildingEntity newBuildingEntity =  PurchaseNewBuildingEntity.builder()
//                .id(purchaseNewBuildingDto.getId())
//                .announcementId(purchaseNewBuildingDto.getAnnouncementId())
//                .repair(purchaseNewBuildingDto.getRepair())
//                .category(purchaseNewBuildingDto.getCategory())
//                .location(purchaseNewBuildingDto.getLocation())
//                .price(purchaseNewBuildingDto.getPrice())
//                .countOfFloor(purchaseNewBuildingDto.getCountOfFloor())
//                .latitude(purchaseNewBuildingDto.getLatitude())
//                .longitude(purchaseNewBuildingDto.getLongitude())
//                .extract(purchaseNewBuildingDto.getExtract())
//                .rooms(purchaseNewBuildingDto.getRooms())
//                .area(purchaseNewBuildingDto.getArea())
//                .build();
//
//
//        return    purchaseNewBuildingRepository.save(newBuildingEntity);

        }

    }




