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
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PurchaseAveragePrice {

    private final PurchaseNewBuildingRepository repository;








    public Long setAveragePrice(AveragePriceDto dto) throws IOException {

        List<PurchaseNewBuildingEntity> allByLocation = repository.findAllByLocationAndAndRooms(dto.getLocation(),dto.getRooms());



        for (PurchaseNewBuildingEntity e : allByLocation){
            if (!dto.getRooms().equals("")){
          allByLocation= allByLocation.stream()
                       .filter(str -> Objects.equals(str.getRooms(),dto.getRooms())).collect(Collectors.toList());
            }
        }


        Long averagePrice =0L;


        for (PurchaseNewBuildingEntity e : allByLocation){
            long l =Long.parseLong(e.getPrice().replaceAll(" ",""));
            averagePrice+=l;

        }
        averagePrice = averagePrice/allByLocation.size();

        return averagePrice;

    }











    }


