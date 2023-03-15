package com.bina.az.binaazdata.service;

import com.bina.az.binaazdata.JsoupService.purchase.JsoupPurchaseNewBuilding;
import com.bina.az.binaazdata.dto.purchase.AveragePriceDto;
import com.bina.az.binaazdata.entity.PurchaseNewBuildingEntity;
import com.bina.az.binaazdata.repository.PurchaseNewBuildingRepository;
import io.swagger.annotations.ApiModelProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PurchaseAveragePrice {

    private final PurchaseNewBuildingRepository repository;


    Long averagePrice =0L;





    public Long setAveragePrice(AveragePriceDto dto) throws IOException {

        ArrayList<PurchaseNewBuildingEntity> allByLocation = repository.findAllByLocationAndAndRooms(dto);

        for (PurchaseNewBuildingEntity e : allByLocation){
            if (!dto.getRooms().equals("")){
                allByLocation.stream()
                        .filter(allByLocation.stream().collect());
            }
        }



        for (PurchaseNewBuildingEntity e : allByLocation){
            long l =Long.parseLong(e.getPrice().replaceAll(" ",""));
            averagePrice+=l;

        }
        averagePrice = averagePrice/allByLocation.size();

        return averagePrice;

    }











    }


