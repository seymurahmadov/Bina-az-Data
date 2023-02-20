package com.bina.az.binaazdata.service;

import com.bina.az.binaazdata.Jsoup.purchase.JsoupPurchaseNewBuilding;
import com.bina.az.binaazdata.dto.purchase.PurchaseNewBuildingDto;
import com.bina.az.binaazdata.entity.PurchaseNewBuildingEntity;
import com.bina.az.binaazdata.repository.PurchaseNewBuildingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PurchaseNewBuildingService {

private final PurchaseNewBuildingRepository purchaseNewBuildingRepository;
private final JsoupPurchaseNewBuilding jsoupPurchaseNewBuildingService;


//
//    public PurchaseNewBuildingEntity addNewPurchaseData(PurchaseNewBuildingDto purchaseDto){
//        PurchaseNewBuildingEntity purchaseNewBuildingEntity= PurchaseNewBuildingEntity.builder()
//                .id(purchaseDto.getId())
//                .announcementId(purchaseDto.getAnnouncementId())
//                .price(purchaseDto.getPrice())
//                .location(purchaseDto.getLocation())
//                .extract(purchaseDto.getExtract())
//                .repair(purchaseDto.getRepair())
//                .rooms(purchaseDto.getRooms())
//                .countOfFloor(purchaseDto.getCountOfFloor())
//                .squareMeter(purchaseDto.getArea())
//                .category(purchaseDto.getCategory())
//                .build();
//
//        return purchaseNewBuildingRepository.save(purchaseNewBuildingEntity);
//    }

    public PurchaseNewBuildingEntity saveNewBuildingJsoupData() throws IOException {
        ArrayList <PurchaseNewBuildingDto>newBuildingDto= jsoupPurchaseNewBuildingService.purchaseJsoupNewBuildingData();


        for (PurchaseNewBuildingDto item : newBuildingDto) {
            PurchaseNewBuildingEntity newBuildingEntity = new PurchaseNewBuildingEntity();

            newBuildingEntity.setId(item.getId());
            newBuildingEntity.setAnnouncementId(item.getAnnouncementId());
                newBuildingEntity.setRepair(item.getRepair());
            newBuildingEntity.setCategory(item.getCategory());
            newBuildingEntity.setLocation(item.getLocation());
            newBuildingEntity.setPrice(item.getPrice());
            newBuildingEntity.setCountOfFloor(item.getCountOfFloor());
            newBuildingEntity.setLatitude(item.getLatitude());
            newBuildingEntity.setExtract(item.getExtract());
            newBuildingEntity.setLongitude(item.getLongitude());
            newBuildingEntity.setRooms(item.getRooms());
            newBuildingEntity.setArea(item.getArea());

          return   purchaseNewBuildingRepository.save(newBuildingEntity);

        }

        return null;
    }



}
