package com.bina.az.binaazdata.service;

import com.bina.az.binaazdata.dto.purchase.AveragePriceDto;
import com.bina.az.binaazdata.dto.purchase.AveragePriceDto2;
import com.bina.az.binaazdata.entity.PurchaseNewBuildingEntity;
import com.bina.az.binaazdata.repository.PurchaseNewBuildingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PurchaseAveragePrice {

    private final PurchaseNewBuildingRepository repository;

    public Long setAveragePrice(AveragePriceDto dto) throws IOException, ParseException {


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




        Date dateFrom = dto.getDateFrom();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String after = formatter.format(dateFrom);
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(after);



        //BeforeFromDate
        allByLocation = allByLocation.stream()
                .filter(strDateBefore-> date.before(strDateBefore.getDate())).collect(Collectors.toList());

        //AfterFromDate
        allByLocation = allByLocation.stream()
                .filter(strDateBefore -> date.after(strDateBefore.getDate())).collect(Collectors.toList());


        //According to Price


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

    public void findBetweenPrice(AveragePriceDto2 dto2){
        List<PurchaseNewBuildingEntity> allByPrice = repository.findAllByPrice(dto2.getFirstPrice(), dto2.getLastPrice());

        for (PurchaseNewBuildingEntity s : allByPrice){
//            if (Long.parseLong(s.getPrice()) > Long.parseLong(dto2.getFirstPrice()) &&
//                    Long.parseLong(s.getPrice()) <  Long.parseLong(dto2.getLastPrice())){
            Long first =Long.parseLong(dto2.getFirstPrice());
            Long second = Long.parseLong(dto2.getLastPrice());


                allByPrice=allByPrice.stream()
                        .filter(strPrice -> Long.parseLong(strPrice.getPrice())> first &&
                                Long.parseLong(strPrice.getPrice()) < second).collect(Collectors.toList());
//            }
        }


    }
}


