package com.bina.az.binaazdata.service;

import com.bina.az.binaazdata.dto.purchase.PurchaseNewBuildingDto;
import com.bina.az.binaazdata.entity.PurchaseNewBuildingEntity;
import com.bina.az.binaazdata.repository.PurchaseNewBuildingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PurchaseNewBuildingService {

private final PurchaseNewBuildingRepository purchaseNewBuildingRepository;
    public PurchaseNewBuildingService(PurchaseNewBuildingRepository purchaseNewBuildingRepository) {
        this.purchaseNewBuildingRepository = purchaseNewBuildingRepository;
    }

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

    @Transactional
    public PurchaseNewBuildingEntity saveNewBuildingJsoupData(List<PurchaseNewBuildingDto> dtoList) {

        for (PurchaseNewBuildingDto item : dtoList) {
            PurchaseNewBuildingEntity newBuildingEntity = new PurchaseNewBuildingEntity();
            newBuildingEntity.setId(item.getId());
            newBuildingEntity.setAnnouncementId(item.getAnnouncementId());
            newBuildingEntity.setRepair(item.getRepair());
            newBuildingEntity.setCategory(item.getCategory());
            newBuildingEntity.setLocation(item.getLocation());
            newBuildingEntity.setPrice(item.getPrice());
            newBuildingEntity.setCountOfFloor(item.getCountOfFloor());
            newBuildingEntity.setLatitude(item.getLatitude());
            newBuildingEntity.setLocation(item.getLongitude());
            newBuildingEntity.setExtract(item.getExtract());

          return   purchaseNewBuildingRepository.save(newBuildingEntity);

        }

        return null;
    }



}
