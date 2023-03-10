package com.bina.az.binaazdata.util;

import com.bina.az.binaazdata.dto.purchase.PurchaseOldBuildingDto;
import com.bina.az.binaazdata.entity.PurchaseOldBuildingEntity;

public class PurchaseOldBuildingUtil {

  public PurchaseOldBuildingEntity oldBuildingUtil (PurchaseOldBuildingDto dto){
        PurchaseOldBuildingEntity oldBuildingEntity = PurchaseOldBuildingEntity.builder()
                .id(dto.getId())
                .announcementId(dto.getAnnouncementId())
                .price(dto.getPrice())
                .location(dto.getLocation())
                .extract(dto.getExtract())
                .repair(dto.getRepair())
                .rooms(dto.getRooms())
                .countOfFloor(dto.getCountOfFloor())
                .area(dto.getArea())
                .category(dto.getCategory())
                .latitude(dto.getLatitude())
                .longitude(dto.getLongitude())
                .date(dto.getDate())
                .build();

        return oldBuildingEntity;

    }
}
