package com.bina.az.binaazdata.service;

import com.bina.az.binaazdata.dto.purchase.serviceDto.*;
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
public class PurchaseNewBuildingService {

    private final PurchaseNewBuildingRepository repository;

    //AveragePriceService
    public Long setAveragePrice(AveragePriceDto dto) throws IOException, ParseException {


        //According to Location
        List<PurchaseNewBuildingEntity> allByLocation = repository.findAllByLocation(dto.getLocation());

        if (!dto.getRooms().equals("")) {
            allByLocation = allByLocation.stream()
                    .filter(str -> Objects.equals(str.getRooms(), dto.getRooms())).collect(Collectors.toList());

        }

        if (!dto.getExtract().equals("")) {
            allByLocation = allByLocation.stream()
                    .filter(strExt -> Objects.equals(strExt.getExtract(), dto.getExtract())).collect(Collectors.toList());
        }

        if (!dto.getRepair().equals("")) {
            allByLocation = allByLocation.stream()
                    .filter(strRepair -> Objects.equals(strRepair.getRepair(), dto.getRepair())).collect(Collectors.toList());
        }

        if (!dto.getFloor().equals("")) {
            allByLocation = allByLocation.stream()
                    .filter(strFloor -> Objects.equals(strFloor.getCountOfFloor(), dto.getFloor())).collect(Collectors.toList());
        }


        Date dateFrom = dto.getDateFrom();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String after = formatter.format(dateFrom);
        Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(after);

        Date dateTo = dto.getDateTo();
        SimpleDateFormat formatterTo = new SimpleDateFormat("yyyy-MM-dd");
        String before = formatter.format(dateTo);
        Date date2 = new SimpleDateFormat("yyyy-MM-dd").parse(before);


        //BeforeFromDate
        allByLocation = allByLocation.stream()
                .filter(strDateBefore -> date1.before(strDateBefore.getDate())).collect(Collectors.toList());

        //AfterFromDate
        allByLocation = allByLocation.stream()
                .filter(strDateBefore -> date2.after(strDateBefore.getDate())).collect(Collectors.toList());


        //Average Price Logic
        Long averagePrice = 0L;


        for (PurchaseNewBuildingEntity e : allByLocation) {
            long l = e.getPrice();
            averagePrice += l;

        }

        try {
            averagePrice = averagePrice / allByLocation.size();

        } catch (ArithmeticException e) {
            return 0L;
        }

        return averagePrice;

    }

    //BetweenPriceService
    public List<PurchaseNewBuildingEntity> findBetweenPrice(BetweenPricesDto betweenPrices) {
        return repository.findAllByPriceBetween(betweenPrices.getMinPrice(), betweenPrices.getMaxPrice());
    }


    //Between Dates
    public List<PurchaseNewBuildingEntity> findBetweenDate(BetweenDateDto betweenDate) {
        return repository.findAllByDateBetween(betweenDate.getFirstDate(), betweenDate.getLastDate());
    }

    //BetweenArea
    public List<PurchaseNewBuildingEntity> findBetweenArea(BetweenAreaDto betweenArea) {
        return repository.findAllByAreaBetween(betweenArea.getMinArea(), betweenArea.getMaxArea());
    }

    //GeneralSortByLocByRoomByRepByExt
    public List<PurchaseNewBuildingEntity> findByLocAndRoomAndRepAndExt(GeneralServiceDto generalDto) {

        List<PurchaseNewBuildingEntity> allByGeneral =
                repository.findAllByLocationAndRoomsAndExtractAndRepairAndPriceBetweenAndAreaBetween(generalDto.getLocation(), generalDto.getRooms(),
                        generalDto.getExtract(), generalDto.getRepair(), generalDto.getMinPrice(), generalDto.getMaxPrice(), generalDto.getMinArea(),
                        generalDto.getMaxArea());
        return allByGeneral;
    }


}