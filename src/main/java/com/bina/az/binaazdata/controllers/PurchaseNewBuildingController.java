package com.bina.az.binaazdata.controllers;

import com.bina.az.binaazdata.JsoupService.purchase.JsoupPurchaseNewBuilding;
import com.bina.az.binaazdata.dto.purchase.serviceDto.*;
import com.bina.az.binaazdata.dto.purchase.PurchaseNewBuildingDto;
import com.bina.az.binaazdata.entity.PurchaseNewBuildingEntity;
import com.bina.az.binaazdata.service.PurchaseNewBuildingService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/binaazdata")
@SecurityRequirement(name = "Bearer Authentication")
@RequiredArgsConstructor
public class PurchaseNewBuildingController {


    private final JsoupPurchaseNewBuilding jsoupPurchaseNewBuilding;

    //SaveToDataBase
    @GetMapping("/add-purchase-new-building-data")
    public PurchaseNewBuildingDto addNewBuildingData() throws IOException {
      return jsoupPurchaseNewBuilding.purchaseJsoupNewBuildingData();
    }

    //AveragePrice
    private final PurchaseNewBuildingService newBuildingService;

    @PostMapping("/average-price")
    public Long setNewBuildingService(@RequestBody AveragePriceDto dto) throws IOException, ParseException {
        return newBuildingService.setAveragePrice(dto);
    }

    //BetweenPrices
    @PostMapping ("/between-prices")
    public List<PurchaseNewBuildingEntity> setBetweenPrice(@Valid @RequestBody BetweenPricesDto dto2)  {
        return newBuildingService.findBetweenPrice(dto2);
    }

    //BetweenDates
    @PostMapping("/between-dates")
    public List<PurchaseNewBuildingEntity> setBetweenDates(@Valid @RequestBody BetweenDateDto dto){
        return newBuildingService.findBetweenDate(dto);
    }

    //BetweenArea
    @PostMapping("/between-area")
    public List<PurchaseNewBuildingEntity> setBetweenArea(@Valid @RequestBody BetweenAreaDto areaDto){
        return newBuildingService.findBetweenArea(areaDto);
    }

    //GeneralSortByLocByRoomByRepByExt
    @PostMapping("/general-sort")
    public List<PurchaseNewBuildingEntity> generalSort(@Valid @RequestBody GeneralServiceDto dto){
        return newBuildingService.findByLocAndRoomAndRepAndExt(dto);
    }


}
