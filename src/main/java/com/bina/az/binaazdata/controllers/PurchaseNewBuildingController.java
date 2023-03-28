package com.bina.az.binaazdata.controllers;

import com.bina.az.binaazdata.JsoupService.purchase.JsoupPurchaseNewBuilding;
import com.bina.az.binaazdata.dto.purchase.AveragePriceDto;
import com.bina.az.binaazdata.dto.purchase.BetweenPricesDto;
import com.bina.az.binaazdata.dto.purchase.PurchaseNewBuildingDto;
import com.bina.az.binaazdata.entity.PurchaseNewBuildingEntity;
import com.bina.az.binaazdata.service.PurchaseNewBuildingService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/binaazdata")
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
    public List<PurchaseNewBuildingEntity> setBetweenPrice(@RequestBody BetweenPricesDto dto2)  {
        return newBuildingService.findBetweenPrice(dto2);
    }
}
