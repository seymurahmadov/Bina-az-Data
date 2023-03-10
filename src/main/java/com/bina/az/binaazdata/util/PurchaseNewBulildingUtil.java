package com.bina.az.binaazdata.util;

import com.bina.az.binaazdata.dto.purchase.PurchaseNewBuildingDto;
import com.bina.az.binaazdata.entity.PurchaseNewBuildingEntity;

public class PurchaseNewBulildingUtil {

    public  PurchaseNewBuildingEntity newBuilding (PurchaseNewBuildingDto dto){
        PurchaseNewBuildingEntity newBuildingEntity =  PurchaseNewBuildingEntity.builder()
                .id(dto.getId())
                .announcementId(dto.getAnnouncementId())
                .repair(dto.getRepair())
                .category(dto.getCategory())
                .location(dto.getLocation())
                .price(dto.getPrice())
                .countOfFloor(dto.getCountOfFloor())
                .latitude(dto.getLatitude())
                .longitude(dto.getLongitude())
                .extract(dto.getExtract())
                .rooms(dto.getRooms())
                .area(dto.getArea())
                .date(dto.getDate())
                .build();

        return newBuildingEntity;

    }
}
