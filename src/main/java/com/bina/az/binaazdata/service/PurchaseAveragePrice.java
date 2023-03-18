package com.bina.az.binaazdata.service;

import com.bina.az.binaazdata.dto.purchase.AveragePriceDto;
import com.bina.az.binaazdata.entity.PurchaseNewBuildingEntity;
import com.bina.az.binaazdata.repository.PurchaseNewBuildingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PurchaseAveragePrice {

    private final PurchaseNewBuildingRepository repository;

    public Long setAveragePrice(AveragePriceDto dto) throws IOException {


        //According to Location
        List<PurchaseNewBuildingEntity> allByLocation = repository.findAllByLocation(dto.getLocation());

        for (PurchaseNewBuildingEntity e : allByLocation){
            if (!dto.getRooms().equals("") ){
          allByLocation= allByLocation.stream()
                       .filter(str -> Objects.equals(str.getRooms(),dto.getRooms())).collect(Collectors.toList());

            }

            if (!dto.getExtract().equals("")){
                allByLocation=allByLocation.stream()
                        .filter(strExt -> Objects.equals(strExt.getExtract(),dto.getExtract())).collect(Collectors.toList());
            }

            if (!dto.getRepair().equals("")){
                allByLocation = allByLocation.stream()
                        .filter(strRepair -> Objects.equals(strRepair.getRepair(),dto.getRepair())).collect(Collectors.toList());
            }

            if (!dto.getFloor().equals("")){
                allByLocation = allByLocation.stream()
                        .filter(strFloor -> Objects.equals(strFloor.getCountOfFloor(),dto.getFloor())).collect(Collectors.toList());
            }
     }


        Long averagePrice =0L;


        for (PurchaseNewBuildingEntity e : allByLocation){
            long l =Long.parseLong(e.getPrice().replaceAll(" ",""));
            averagePrice+=l;

        }

        try {
            averagePrice = averagePrice/allByLocation.size();

        }catch (ArithmeticException e){
            return 0L;
        }

        return averagePrice;

    }
}


