package com.bina.az.binaazdata.service;

import com.bina.az.binaazdata.dto.purchase.PurchaseNewBuildingDto;
import com.bina.az.binaazdata.entity.PurchaseEntity;
import com.bina.az.binaazdata.repository.PurchaseRepository;
import org.springframework.stereotype.Service;

@Service
public class PurchaseService {

private final PurchaseRepository purchaseRepository;

    public PurchaseService(PurchaseRepository purchaseRepository) {
        this.purchaseRepository = purchaseRepository;
    }

    public PurchaseEntity addNewPurchaseData(PurchaseNewBuildingDto purchaseDto){
        PurchaseEntity purchaseEntity= PurchaseEntity.builder()
                .id(purchaseDto.getId())
                .announcementId(purchaseDto.getAnnouncementId())
                .price(purchaseDto.getPrice())
                .location(purchaseDto.getLocation())
                .extract(purchaseDto.getExtract())
                .repair(purchaseDto.getRepair())
                .rooms(purchaseDto.getRooms())
                .countOfFloor(purchaseDto.getCountOfFloor())
                .squareMeter(purchaseDto.getArea())
                .category(purchaseDto.getCategory())
                .build();

        return purchaseRepository.save(purchaseEntity);
    }



}
